package com.chat.qs.word;

import org.apdplat.word.analysis.CosineTextSimilarity;
import org.apdplat.word.analysis.EditDistanceTextSimilarity;
import org.apdplat.word.analysis.EuclideanDistanceTextSimilarity;
import org.apdplat.word.analysis.JaccardTextSimilarity;
import org.apdplat.word.analysis.JaroDistanceTextSimilarity;
import org.apdplat.word.analysis.JaroWinklerDistanceTextSimilarity;
import org.apdplat.word.analysis.ManhattanDistanceTextSimilarity;
import org.apdplat.word.analysis.SimHashPlusHammingDistanceTextSimilarity;
import org.apdplat.word.analysis.SimpleTextSimilarity;
import org.apdplat.word.analysis.SrensenDiceCoefficientTextSimilarity;
import org.apdplat.word.analysis.TextSimilarity;

/**
 * 文本相似度
 */
public class TextSimilarityUtil {

    private TextSimilarityUtil(){}

    private static class TextSimilarityInstance {
        private static final TextSimilarity cosineTextSimilarity = new CosineTextSimilarity();
        private static final TextSimilarity simpleTextSimilarity = new SimpleTextSimilarity();
        private static final TextSimilarity editDistanceTextSimilarity = new EditDistanceTextSimilarity();
        private static final TextSimilarity simHashPlusHammingDistanceTextSimilarity = new SimHashPlusHammingDistanceTextSimilarity();
        private static final TextSimilarity jaccardTextSimilarity = new JaccardTextSimilarity();
        private static final TextSimilarity euclideanDistanceTextSimilarity = new EuclideanDistanceTextSimilarity();
        private static final TextSimilarity manhattanDistanceTextSimilarity = new ManhattanDistanceTextSimilarity();
        private static final TextSimilarity jaroDistanceTextSimilarity = new JaroDistanceTextSimilarity();
        private static final TextSimilarity jaroWinklerDistanceTextSimilarity = new JaroWinklerDistanceTextSimilarity();
        private static final TextSimilarity srensenDiceCoefficientTextSimilarity = new SrensenDiceCoefficientTextSimilarity();
    }

    public static double textSimilarity(int type,String text1,String text2){
        switch (type){
            case 0 : return cosineTextSimilarity(text1,text2);
            case 1 : return simpleTextSimilarity(text1,text2);
            case 2 : return editDistanceTextSimilarity(text1,text2);
            case 3 : return 0;//return simHashPlusHammingDistanceTextSimilarity(text1,text2);
            case 4 : return jaccardTextSimilarity(text1,text2);
            case 5 : return 0;//return euclideanDistanceTextSimilarity(text1,text2);
            case 6 : return 0;//return manhattanDistanceTextSimilarity(text1,text2);
            case 7 : return 0;//return jaroDistanceTextSimilarity(text1,text2);
            case 8 : return jaroWinklerDistanceTextSimilarity(text1,text2);
            case 9 : return srensenDiceCoefficientTextSimilarity(text1,text2);
            default: return 0;
        }
    }

    private static void log(String typeName,String text1,String text2,double score){
        System.out.println("["+typeName+"] `"+text1+"` 和 `"+text2+"` 的相似度分值："+score);
    }

    /**
     * 方式一：余弦相似度，通过计算两个向量的夹角余弦值来评估他们的相似度
     */
    public static double cosineTextSimilarity(String text1,String text2){
        TextSimilarity textSimilarity = TextSimilarityInstance.cosineTextSimilarity;
        double score = textSimilarity.similarScore(text1, text2);
        log("余弦相似度",text1,text2,score);
        return score;
    }

    /**
     * 方式二：简单共有词，通过计算两篇文档共有的词的总字符数除以最长文档字符数来评估他们的相似度
     */
    public static double simpleTextSimilarity(String text1,String text2){
        TextSimilarity textSimilarity = TextSimilarityInstance.simpleTextSimilarity;
        double score = textSimilarity.similarScore(text1, text2);
        log("简单共有词",text1,text2,score);
        return score;
    }

    /**
     * 方式三：编辑距离，通过计算两个字串之间由一个转成另一个所需的最少编辑操作次数来评估他们的相似度
     */
    public static double editDistanceTextSimilarity(String text1,String text2){
        TextSimilarity textSimilarity = TextSimilarityInstance.editDistanceTextSimilarity;
        double score = textSimilarity.similarScore(text1, text2);
        log("编辑距离",text1,text2,score);
        return score;
    }

    /**
     * 方式四：SimHash + 汉明距离，先使用SimHash把不同长度的文本映射为等长文本，然后再计算等长文本的汉明距离
     */
    public static double simHashPlusHammingDistanceTextSimilarity(String text1,String text2){
        TextSimilarity textSimilarity = TextSimilarityInstance.simHashPlusHammingDistanceTextSimilarity;
        double score = textSimilarity.similarScore(text1, text2);
        log("SimHash+汉明距离",text1,text2,score);
        return score;
    }

    /**
     * 方式五：Jaccard相似性系数（Jaccard similarity coefficient），通过计算两个集合交集的大小除以并集的大小来评估他们的相似度
     */
    public static double jaccardTextSimilarity(String text1,String text2){
        TextSimilarity textSimilarity = TextSimilarityInstance.jaccardTextSimilarity;
        double score = textSimilarity.similarScore(text1, text2);
        log("Jaccard相似性系数",text1,text2,score);
        return score;
    }

    /**
     * 方式六：欧几里得距离（Euclidean Distance），通过计算两点间的距离来评估他们的相似度
     */
    public static double euclideanDistanceTextSimilarity(String text1,String text2){
        TextSimilarity textSimilarity = TextSimilarityInstance.euclideanDistanceTextSimilarity;
        double score = textSimilarity.similarScore(text1, text2);
        log("欧几里得距离",text1,text2,score);
        return score;
    }

    /**
     * 方式七：曼哈顿距离（Manhattan Distance），通过计算两个点在标准坐标系上的绝对轴距总和来评估他们的相似度
     */
    public static double manhattanDistanceTextSimilarity(String text1,String text2){
        TextSimilarity textSimilarity = TextSimilarityInstance.manhattanDistanceTextSimilarity;
        double score = textSimilarity.similarScore(text1, text2);
        log("曼哈顿距离",text1,text2,score);
        return score;
    }

    /**
     * 方式八：Jaro距离（Jaro Distance），编辑距离的一种类型
     */
    public static double jaroDistanceTextSimilarity(String text1,String text2){
        TextSimilarity textSimilarity = TextSimilarityInstance.jaroDistanceTextSimilarity;
        double score = textSimilarity.similarScore(text1, text2);
        log("Jaro距离",text1,text2,score);
        return score;
    }

    /**
     * 方式九：Jaro–Winkler距离（Jaro–Winkler Distance），Jaro的扩展
     */
    public static double jaroWinklerDistanceTextSimilarity(String text1,String text2){
        TextSimilarity textSimilarity = TextSimilarityInstance.jaroWinklerDistanceTextSimilarity;
        double score = textSimilarity.similarScore(text1, text2);
        log("Jaro–Winkler距离",text1,text2,score);
        return score;
    }

    /**
     * 方式十：Sørensen–Dice系数（Sørensen–Dice coefficient），通过计算两个集合交集的大小的2倍除以两个集合的大小之和来评估他们的相似度
     */
    public static double srensenDiceCoefficientTextSimilarity(String text1,String text2){
        TextSimilarity textSimilarity = TextSimilarityInstance.srensenDiceCoefficientTextSimilarity;
        double score = textSimilarity.similarScore(text1, text2);
        log("Sørensen–Dice系数",text1,text2,score);
        return score;
    }
}
