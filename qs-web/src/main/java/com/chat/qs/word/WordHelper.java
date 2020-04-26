package com.chat.qs.word;

import com.chat.qs.config.redis.RedissonService;
import com.chat.qs.constant.RedisConstant;
import com.chat.qs.entity.Answer;
import com.chat.qs.entity.Category;
import com.chat.qs.entity.City;
import com.chat.qs.entity.Label;
import com.chat.qs.entity.Question;
import com.chat.qs.entity.WeatherType;
import com.chat.qs.enums.QuestionType;
import com.chat.qs.pojo.QuestionScore;
import com.chat.qs.entity.UnknownQuestion;
import com.chat.qs.enums.CommonLanguage;
import com.chat.qs.pojo.weather.Forecast;
import com.chat.qs.pojo.weather.ResultMsg;
import com.chat.qs.service.AnswerService;
import com.chat.qs.service.CategoryService;
import com.chat.qs.service.CityService;
import com.chat.qs.service.LabelService;
import com.chat.qs.service.QuestionService;
import com.chat.qs.service.UnknownQuestionService;
import com.chat.qs.service.WeatherTypeService;
import com.chat.qs.utils.HttpClient;
import com.chat.qs.utils.SocketMessage;
import com.chat.qs.utils.SpringBeanUtil;
import com.chat.qs.utils.StringUtils;
import com.chat.qs.vo.AnswerVo;
import com.chat.qs.vo.CategoryVo;
import com.chat.qs.vo.CityVo;
import com.chat.qs.vo.LabelVo;
import com.chat.qs.vo.QuestionVo;
import com.chat.qs.vo.WeatherTypeVo;
import com.google.gson.Gson;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.analysis.SentenceIdentify;
import org.apdplat.word.recognition.PersonName;
import org.apdplat.word.recognition.Punctuation;
import org.apdplat.word.segmentation.Word;
import org.apdplat.word.util.WordConfTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2020/4/15 0015.
 */
@Component
public class WordHelper {

    Logger logger = LoggerFactory.getLogger(WordHelper.class);

    @Resource
    private QuestionService questionService;
    @Resource
    private AnswerService answerService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private LabelService labelService;
    @Resource
    private UnknownQuestionService unknownQuestionService;
    @Resource
    private CityService cityService;
    @Resource
    private WeatherTypeService weatherTypeService;
    @Resource
    private RedissonService redissonService;

    private volatile static WordHelper instance = null;

    private WordHelper(){}

    public static WordHelper getInstance()   {
        if (instance == null)  {
            synchronized (WordHelper.class) {
                if (instance== null)  {
                    instance= new WordHelper();
                }
            }
        }
        return instance;
    }

    public void init(){
        if(answerService == null){
            answerService = SpringBeanUtil.getBean(AnswerService.class);
        }
        if(questionService == null) {
            questionService = SpringBeanUtil.getBean(QuestionService.class);
        }
        if(redissonService == null) {
            redissonService = SpringBeanUtil.getBean(RedissonService.class);
        }
        if(categoryService == null) {
            categoryService = SpringBeanUtil.getBean(CategoryService.class);
        }
        if(labelService == null) {
            labelService = SpringBeanUtil.getBean(LabelService.class);
        }
        if(unknownQuestionService == null) {
            unknownQuestionService = SpringBeanUtil.getBean(UnknownQuestionService.class);
        }
        if(cityService == null) {
            cityService = SpringBeanUtil.getBean(CityService.class);
        }
        if(weatherTypeService == null) {
            weatherTypeService = SpringBeanUtil.getBean(WeatherTypeService.class);
        }
    }

    /**
     * 初始化缓存拆词
     * @return
     */
    @PostConstruct
    public void initWord(){
        init();
        //缓存问题
        redissonService.deleteRBucket(RedisConstant.QUESTION_LIST_KEY);
        QuestionVo questionVo = new QuestionVo();
        questionVo.setDelFlag(false);
        List<Question> questionList = questionService.selectList(questionVo);
        if(questionList!=null && !questionList.isEmpty()){
            long t1 = System.currentTimeMillis();
            Set<String> wordSet = null;
            int size = (questionList.size()+"").length();
            int i = 1;
            for(Question question : questionList){
                List<Word> wordList = segWord(question.getTitle());
                wordSet = new HashSet<>();
                for(Word word : wordList){
                    wordSet.add(word.getText());
                }
                question.setWordSet(wordSet);
                System.out.println(StringUtils.addZeroForNum(i+"",size)+"、"+question);
                i++;
            }
            redissonService.setRBucket(RedisConstant.QUESTION_LIST_KEY,questionList);
            long t2 = System.currentTimeMillis();
            logger.info("缓存[问题]到redis中,key：{},size：{}",RedisConstant.QUESTION_LIST_KEY,questionList.size());
            logger.info("缓存[问题]耗时：{}ms",(t2-t1));
        }

        //缓存问题说明
        redissonService.deleteRBucket(RedisConstant.ANSWER_LIST_KEY);
        AnswerVo answerVo = new AnswerVo();
        answerVo.setDelFlag(false);
        List<Answer> answerList = answerService.selectList(answerVo);
        if(answerList!=null && !answerList.isEmpty()){
            long t1 = System.currentTimeMillis();
            redissonService.setRBucket(RedisConstant.ANSWER_LIST_KEY,answerList);
            long t2 = System.currentTimeMillis();
            logger.info("缓存[说明]到redis中,key：{},size：{}",RedisConstant.ANSWER_LIST_KEY,answerList.size());
            logger.info("缓存[说明]耗时：{}ms",(t2-t1));
        }

        //缓存问题分类
        redissonService.deleteRBucket(RedisConstant.CATEGORY_LIST_KEY);
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setDelFlag(false);
        List<Category> categoryList = categoryService.selectList(categoryVo);
        if(categoryList!=null && !categoryList.isEmpty()){
            long t1 = System.currentTimeMillis();
            redissonService.setRBucket(RedisConstant.CATEGORY_LIST_KEY,categoryList);
            long t2 = System.currentTimeMillis();
            logger.info("缓存[分类]到redis中,key：{},size：{}",RedisConstant.CATEGORY_LIST_KEY,categoryList.size());
            logger.info("缓存[分类]耗时：{}ms",(t2-t1));
        }

        //缓存问题标签
        redissonService.deleteRBucket(RedisConstant.LABEL_LIST_KEY);
        LabelVo labelVo = new LabelVo();
        labelVo.setDelFlag(false);
        List<Label> labelList = labelService.selectList(labelVo);
        if(labelList!=null && !labelList.isEmpty()){
            long t1 = System.currentTimeMillis();
            redissonService.setRBucket(RedisConstant.LABEL_LIST_KEY,labelList);
            long t2 = System.currentTimeMillis();
            logger.info("缓存[标签]到redis中,key：{},size：{}",RedisConstant.LABEL_LIST_KEY,labelList.size());
            logger.info("缓存[标签]耗时：{}ms",(t2-t1));
        }

        //缓存城市
        redissonService.deleteRBucket(RedisConstant.CITY_LIST_KEY);
        CityVo cityVo = new CityVo();
        List<City> cityList = cityService.selectList(cityVo);
        if(cityList == null || cityList.isEmpty()){
            Properties prop = new Properties();
            City city = null;
            try {
                InputStreamReader inStream = new InputStreamReader(SpecialHelper.class.getResourceAsStream("/weather.properties"),"utf-8");
                prop.load(inStream);     ///加载属性列表
                Enumeration enu = prop.keys();
                while (enu.hasMoreElements()) {
                    Object obj = enu.nextElement();
                    Object objv = prop.get(obj);
                    city = new City();
                    city.setId(Long.valueOf(obj.toString()));
                    city.setName(objv.toString().trim());
                    cityService.insert(city);
                }
                inStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            cityList = cityService.selectList(cityVo);
        }

        if(cityList!=null && !cityList.isEmpty()){
            long t1 = System.currentTimeMillis();
            redissonService.setRBucket(RedisConstant.CITY_LIST_KEY,cityList);
            long t2 = System.currentTimeMillis();
            logger.info("缓存[城市]到redis中,key：{},size：{}",RedisConstant.CITY_LIST_KEY,cityList.size());
            logger.info("缓存[城市]耗时：{}ms",(t2-t1));
        }


        //缓存天气类型
        redissonService.deleteRBucket(RedisConstant.WEATHER_LIST_KEY);
        WeatherTypeVo weatherTypeVo = new WeatherTypeVo();
        List<WeatherType> weatherTypeList = weatherTypeService.selectList(weatherTypeVo);
        if(weatherTypeList!=null && !weatherTypeList.isEmpty()){
            long t1 = System.currentTimeMillis();
            redissonService.setRBucket(RedisConstant.WEATHER_LIST_KEY,weatherTypeList);
            long t2 = System.currentTimeMillis();
            logger.info("缓存[天气]到redis中,key：{},size：{}",RedisConstant.WEATHER_LIST_KEY,weatherTypeList.size());
            logger.info("缓存[天气]耗时：{}ms",(t2-t1));
        }
    }

    //相似度匹配前过滤部分问题
    public List<Question> handleQuestion(Set<String> wordSet,List<Question> questionList){
        List<Question> list = new ArrayList<>();
        for(Question question : questionList){
            for (String word : wordSet){
                if (question.getTitle().indexOf(word)!=-1 || word.indexOf(question.getTitle())!=-1){
                    list.add(question);
                    break;
                }
                for(String str : question.getWordSet()){
                    if(word.indexOf(str)!=-1){
                        list.add(question);
                        break;
                    }
                }
            }
        }
        return list;
    }

    /**
     * 拆词
     * @param text
     * @return
     */
    public SocketMessage seg(String text){
        init();
        List<Word> list = segWord(text);
        Set<String> wordSet = new HashSet<>();
        for(Word word : list){
            wordSet.add(word.getText());
        }
        System.out.println(list);
        //根据 标签或标题 获取问题列表
        List<Question> questionList = redissonService.getRBucket(RedisConstant.QUESTION_LIST_KEY);
        questionList = handleQuestion(wordSet,questionList);
        if(questionList==null){
            initWord();
            questionList = redissonService.getRBucket(RedisConstant.QUESTION_LIST_KEY);
        }

        if(questionList == null || questionList.isEmpty()){
            // 添加未知问题
            saveUnknowQuestion(text);
            return SocketMessage.text(CommonLanguage.NOT_FOUND_QUESTION.getDesc());
        }
        Question q = new Question();
        q.setTitle(text);
        q.setWordSet(wordSet);
        List<Question> qList = similarity(q,questionList);
        if(qList.isEmpty()){
            // 添加未知问题
            saveUnknowQuestion(text);
            return SocketMessage.text(CommonLanguage.NOT_FOUND_QUESTION.getDesc());
        }else if(qList.size()>1){
            List<String> linkList = new ArrayList<>();
            for(Question question : qList){
                linkList.add(question.getTitle());
            }
            return SocketMessage.link(linkList);
        }else{
            //特殊问题
            if(!qList.get(0).getType().equals(QuestionType.common.getCode())){
                return SpecialHelper.getInstance().handle(qList.get(0),text);
            }
        }

        //获取说明列表
        List<Answer> answerList = redissonService.getRBucket(RedisConstant.ANSWER_LIST_KEY);
        Answer answer = null;
        for(Answer a : answerList){
            if(a.getQuestionId().equals(qList.get(0).getId())){
                answer = a;
            }
        }

        if(answer == null){
            return SocketMessage.text(CommonLanguage.NOT_FOUND_ANSWER.getDesc());
        }else{
            return SocketMessage.text(answer.getContent());
        }
    }

    private static final boolean KEEP_PUNCTUATION = WordConfTools.getBoolean("keep.punctuation", false);
    private static final boolean PARALLEL_SEG = WordConfTools.getBoolean("parallel.seg", true);
    private static final boolean KEEP_WHITESPACE = WordConfTools.getBoolean("keep.whitespace", false);
    private static final boolean KEEP_CASE = WordConfTools.getBoolean("keep.case", false);
    private static final boolean PERSON_NAME_RECOGNIZE = WordConfTools.getBoolean("person.name.recognize", true);

    private List<Word> segWord(String text) {
        List<String> sentences = Punctuation.seg(text, KEEP_PUNCTUATION);
        if(sentences.size() == 1){
            return segSentence(sentences.get(0));
        }
        if(!PARALLEL_SEG){
            //串行顺序处理，不能利用多核优势
            return sentences.stream().flatMap(sentence->segSentence(sentence).stream()).collect(Collectors.toList());
        }
        //如果是多个句子，可以利用多核提升分词速度
        Map<Integer, String> sentenceMap = new HashMap<>();
        int len = sentences.size();
        for(int i=0; i<len; i++){
            //记住句子的先后顺序，因为后面的parallelStream方法不保证顺序
            sentenceMap.put(i, sentences.get(i));
        }
        //用数组收集句子分词结果
        List<Word>[] results = new List[sentences.size()];
        //使用Java8中内置的并行处理机制
        sentenceMap.entrySet().parallelStream().forEach(entry -> {
            int index = entry.getKey();
            String sentence = entry.getValue();
            results[index] = segSentence(sentence);
        });
        sentences.clear();
        sentences = null;
        sentenceMap.clear();
        sentenceMap = null;
        List<Word> resultList = new ArrayList<>();
        for(List<Word> result : results){
            resultList.addAll(result);
        }
        return resultList;
    }

    /**
     * 将句子切分为词
     * @param sentence 句子
     * @return 词集合
     */
    private List<Word> segSentence(final String sentence){
        if(sentence.length() == 1){
            if(KEEP_WHITESPACE){
                List<Word> result = new ArrayList<>(1);
                result.add(new Word(KEEP_CASE ? sentence : sentence.toLowerCase()));
                return result;
            }else{
                if(!Character.isWhitespace(sentence.charAt(0))){
                    List<Word> result = new ArrayList<>(1);
                    result.add(new Word(KEEP_CASE ? sentence : sentence.toLowerCase()));
                    return result;
                }
            }
        }
        if(sentence.length() > 1){
            List<Word> list = WordSegmenter.seg(sentence);//分词
            if(list != null){
                if(PERSON_NAME_RECOGNIZE){
                    list = PersonName.recognize(list);
                }
                return list;
            }else{
                System.out.println("文本 "+sentence+" 没有获得分词结果");
            }
        }
        return Collections.emptyList();
    }

    /**
     * 相似度匹配
     * @return
     */
    public List<Question> similarity(Question q,List<Question> questionList){
        long t1 = System.currentTimeMillis();
        //循环匹配的问题列表
        for (Question question : questionList){
            //使用6种相似度算法
            for (int i=0;i<10;i++){
                //用户输入的问题，拆词集合
                for(String queryWord : q.getWordSet()){
                    //匹配的问题的拆词集合
                    for(String listWord : question.getWordSet()){
                        //计算相似度分值
                        double score = TextSimilarityUtil.textSimilarity(i,queryWord,listWord);
                        //统计相似度分值
                        handleScore(i,score,question);
                    }
                }

            }
        }
        //筛选匹配的问题集合，去除 只要有一种算法分值为0的问题
        Iterator<Question> iterator = questionList.iterator();
        while(iterator.hasNext()){
            QuestionScore score = iterator.next().getQuestionScore();
            if( score.getCosineTextSimilarity()==0||score.getSimpleTextSimilarity()==0||
                score.getEditDistanceTextSimilarity()==0||score.getJaccardTextSimilarity()==0||
                score.getJaroWinklerDistanceTextSimilarity()==0||score.getSrensenDiceCoefficientTextSimilarity()==0
            ){
                iterator.remove();
            }
        }

        questionList.sort(new Comparator<Question>() {
            public int compare(Question o1, Question o2) {
                if ((o2.getQuestionScore().getTotal() - o1.getQuestionScore().getTotal()) > 0) {
                    return 1;
                } else if ((o2.getQuestionScore().getTotal() - o1.getQuestionScore().getTotal()) == 0){
                    return 0;
                }else{
                    return-1;
                }
            }
        });

        List<Question> qList = new ArrayList<>();
        for(Question question : questionList){
            double total = question.getQuestionScore().getTotal();
            System.out.println(question.getTitle()+" : " + total);
            if(total == questionList.get(0).getQuestionScore().getTotal()){
                if(total!=0){
                    qList.add(question);
                }
            }
        }

        long t2 = System.currentTimeMillis();
        logger.info("相似度匹配耗时：{}ms",(t2-t1));
        return qList;
    }

    public void handleScore(int type,double score,Question question){
        QuestionScore questionScore = question.getQuestionScore();
        if(questionScore == null){
            questionScore = new QuestionScore();
            question.setQuestionScore(questionScore);
        }
        if(type == 0){
            double s = questionScore.getCosineTextSimilarity()+score;
            questionScore.setCosineTextSimilarity(s);
        }else if(type == 1){
            double s = questionScore.getSimpleTextSimilarity()+score;
            questionScore.setSimpleTextSimilarity(s);
        }else if(type == 2){
            double s = questionScore.getEditDistanceTextSimilarity()+score;
            questionScore.setEditDistanceTextSimilarity(s);
        }else if(type == 3){
            double s = questionScore.getSimHashPlusHammingDistanceTextSimilarity()+score;
            questionScore.setSimHashPlusHammingDistanceTextSimilarity(s);
        }else if(type == 4){
            double s = questionScore.getJaccardTextSimilarity()+score;
            questionScore.setJaccardTextSimilarity(s);
        }else if(type == 5){
            double s = questionScore.getEuclideanDistanceTextSimilarity()+score;
            questionScore.setEuclideanDistanceTextSimilarity(s);
        }else if(type == 6){
            double s = questionScore.getManhattanDistanceTextSimilarity()+score;
            questionScore.setManhattanDistanceTextSimilarity(s);
        }else if(type == 7){
            double s = questionScore.getJaroDistanceTextSimilarity()+score;
            questionScore.setJaroDistanceTextSimilarity(s);
        }else if(type == 8){
            double s = questionScore.getJaroWinklerDistanceTextSimilarity()+score;
            questionScore.setJaroWinklerDistanceTextSimilarity(s);
        }else if(type == 9){
            double s = questionScore.getSrensenDiceCoefficientTextSimilarity()+score;
            questionScore.setSrensenDiceCoefficientTextSimilarity(s);
        }
    }

    public SocketMessage label(Long labelId) {
        init();
        List<Question> questionList = redissonService.getRBucket(RedisConstant.QUESTION_LIST_KEY);
        List<String> linkList = new ArrayList<>();
        for(Question question : questionList){
            for(Label label : question.getLabelList()) {
                if(labelId.equals(label.getId())){
                    linkList.add(question.getTitle());
                    break;
                }
            }
        }
        if(linkList.size()>0){
            return SocketMessage.link(linkList);
        }else{
            return SocketMessage.text(CommonLanguage.NOT_FOUND_QUESTION.getDesc());
        }
    }

    public SocketMessage cate(Long cateId) {
        init();
        List<Category> categoryList = redissonService.getRBucket(RedisConstant.CATEGORY_LIST_KEY);
        childMenu = new ArrayList<>();
        List<Category> list = treeMenuList(categoryList, cateId);
        for(Category category : categoryList){
            if(category.getId().equals(cateId)){
                list.add(category);
            }
        }

        List<String> linkList = new ArrayList<>();
        List<Question> questionList = redissonService.getRBucket(RedisConstant.QUESTION_LIST_KEY);
        for(Question question : questionList){
            for (Category category : list){
                if(category.getId().equals(question.getCateId())){
                    linkList.add(question.getTitle());
                    break;
                }
            }
        }
        if(linkList.size()>0){
            return SocketMessage.link(linkList);
        }else{
            return SocketMessage.text(CommonLanguage.NOT_FOUND_QUESTION.getDesc());
        }
    }


    /**
     * 获取某个父节点下面的所有子节点
     * @param menuList
     * @param pid
     * @return
     */
    private List<Category> childMenu = new ArrayList<>();//子节点
    public List<Category> treeMenuList(List<Category> categoryList, Long pid) {
        for (Category category : categoryList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (category.getPid().equals(pid)) {
                //递归遍历下一级
                treeMenuList(categoryList, category.getId());
                childMenu.add(category);
            }
        }
        return childMenu;
    }


    private static List<Category> listTotree(List<Category> list) {
        List<Category> treeList = new ArrayList<>();
        for (Category tree : list) {
            //找到根
            if (tree.getPid() == 0) {
                treeList.add(tree);
            }
            //找到子
            for (Category treeNode : list) {
                if (treeNode.getPid().equals(tree.getId())) {
                    if (tree.getChildren() == null) {
                        tree.setChildren(new ArrayList<>());
                    }
                    tree.getChildren().add(treeNode);
                }
            }
        }
        return treeList;
    }


    public void saveUnknowQuestion(String title){
        float score = SentenceIdentify.identify(title);
        System.out.println("["+title+"]是一个有意义人话的可能性："+score);
        if(score>=0.5){
            UnknownQuestion unknownQuestion = new UnknownQuestion();
            unknownQuestion.setTitle(title);
            unknownQuestion.setCreateTime(new Date());
            unknownQuestion.setStatus(0);
            unknownQuestionService.insert(unknownQuestion);
        }
    }

}
