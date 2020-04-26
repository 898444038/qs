package com.chat.qs.word.qa;

/**
 * Created by Administrator on 2020/4/16 0016.
 */

import com.google.gson.Gson;
//import org.apdplat.qa.datasource.BaiduDataSource;
//import org.apdplat.qa.model.CandidateAnswer;
//import org.apdplat.qa.model.Question;
//import org.apdplat.qa.system.CommonQuestionAnsweringSystem;
//import org.apdplat.qa.system.QuestionAnsweringSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 将候选答案生成为json格式
 * @author 杨尚川
 */
public class JsonGenerator {

    private static final Logger LOG = LoggerFactory.getLogger(JsonGenerator.class);
    private static final Gson gson = new Gson();

    /*public static String generate(CandidateAnswer candidateAnswer) {
        try {
            return gson.toJson(candidateAnswer);
        } catch (Exception e) {
            LOG.error("生成候选答案的json表示出错", e);
        }
        return "{}";
    }

    public static String generate(List<CandidateAnswer> candidateAnswers) {
        return generate(candidateAnswers, -1);
    }
    public static String generate(List<CandidateAnswer> candidateAnswers, int topN) {
        if(candidateAnswers==null){
            return "[]";
        }
        if(topN > 0){
            int len = candidateAnswers.size();
            if(topN < len){
                List<CandidateAnswer> tempCandidateAnswers = new ArrayList<>(topN);
                for(int i=0; i<topN; i++){
                    tempCandidateAnswers.add(candidateAnswers.get(i));
                }
                candidateAnswers = tempCandidateAnswers;
            }
        }
        try {
            return gson.toJson(candidateAnswers);
        } catch (Exception e) {
            LOG.error("生成候选答案的json表示出错", e);
        }
        return "[]";
    }

    public static void main(String[] args) {
        QuestionAnsweringSystem questionAnsweringSystem = new CommonQuestionAnsweringSystem();
        questionAnsweringSystem.setDataSource(new BaiduDataSource());
        String questionStr = "谁死后布了七十二疑冢？";
        Question question = questionAnsweringSystem.answerQuestion(questionStr);
        if (question != null) {
            List<CandidateAnswer> candidateAnswers = question.getAllCandidateAnswer();
            System.out.println(JsonGenerator.generate(candidateAnswers));
            System.out.println(JsonGenerator.generate(candidateAnswers, 1));
            System.out.println(JsonGenerator.generate(candidateAnswers, 2));
            System.out.println(JsonGenerator.generate(candidateAnswers, 9));
            System.out.println(JsonGenerator.generate(candidateAnswers, 100));
            System.out.println(JsonGenerator.generate(candidateAnswers.get(0)));
        }
    }*/
}
