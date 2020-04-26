package com.chat.qs.pojo;

/**
 * 问题评分
 * Created by Administrator on 2020/4/21 0021.
 */
public class QuestionScore {

    private Long id;

    private double cosineTextSimilarity = 0;
    private double simpleTextSimilarity = 0;
    private double editDistanceTextSimilarity = 0;
    private double simHashPlusHammingDistanceTextSimilarity = 0;
    private double jaccardTextSimilarity = 0;
    private double euclideanDistanceTextSimilarity = 0;
    private double manhattanDistanceTextSimilarity = 0;
    private double jaroDistanceTextSimilarity = 0;
    private double jaroWinklerDistanceTextSimilarity = 0;
    private double srensenDiceCoefficientTextSimilarity = 0;

    private double total;

    public QuestionScore() {
    }

    public double getTotal() {
        return cosineTextSimilarity+simpleTextSimilarity+editDistanceTextSimilarity+
                simHashPlusHammingDistanceTextSimilarity+jaccardTextSimilarity+
                euclideanDistanceTextSimilarity+manhattanDistanceTextSimilarity+
                jaroDistanceTextSimilarity+jaroWinklerDistanceTextSimilarity+
                srensenDiceCoefficientTextSimilarity;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCosineTextSimilarity() {
        return cosineTextSimilarity;
    }

    public void setCosineTextSimilarity(double cosineTextSimilarity) {
        this.cosineTextSimilarity = cosineTextSimilarity;
    }

    public double getSimpleTextSimilarity() {
        return simpleTextSimilarity;
    }

    public void setSimpleTextSimilarity(double simpleTextSimilarity) {
        this.simpleTextSimilarity = simpleTextSimilarity;
    }

    public double getEditDistanceTextSimilarity() {
        return editDistanceTextSimilarity;
    }

    public void setEditDistanceTextSimilarity(double editDistanceTextSimilarity) {
        this.editDistanceTextSimilarity = editDistanceTextSimilarity;
    }

    public double getSimHashPlusHammingDistanceTextSimilarity() {
        return simHashPlusHammingDistanceTextSimilarity;
    }

    public void setSimHashPlusHammingDistanceTextSimilarity(double simHashPlusHammingDistanceTextSimilarity) {
        this.simHashPlusHammingDistanceTextSimilarity = simHashPlusHammingDistanceTextSimilarity;
    }

    public double getJaccardTextSimilarity() {
        return jaccardTextSimilarity;
    }

    public void setJaccardTextSimilarity(double jaccardTextSimilarity) {
        this.jaccardTextSimilarity = jaccardTextSimilarity;
    }

    public double getEuclideanDistanceTextSimilarity() {
        return euclideanDistanceTextSimilarity;
    }

    public void setEuclideanDistanceTextSimilarity(double euclideanDistanceTextSimilarity) {
        this.euclideanDistanceTextSimilarity = euclideanDistanceTextSimilarity;
    }

    public double getManhattanDistanceTextSimilarity() {
        return manhattanDistanceTextSimilarity;
    }

    public void setManhattanDistanceTextSimilarity(double manhattanDistanceTextSimilarity) {
        this.manhattanDistanceTextSimilarity = manhattanDistanceTextSimilarity;
    }

    public double getJaroDistanceTextSimilarity() {
        return jaroDistanceTextSimilarity;
    }

    public void setJaroDistanceTextSimilarity(double jaroDistanceTextSimilarity) {
        this.jaroDistanceTextSimilarity = jaroDistanceTextSimilarity;
    }

    public double getJaroWinklerDistanceTextSimilarity() {
        return jaroWinklerDistanceTextSimilarity;
    }

    public void setJaroWinklerDistanceTextSimilarity(double jaroWinklerDistanceTextSimilarity) {
        this.jaroWinklerDistanceTextSimilarity = jaroWinklerDistanceTextSimilarity;
    }

    public double getSrensenDiceCoefficientTextSimilarity() {
        return srensenDiceCoefficientTextSimilarity;
    }

    public void setSrensenDiceCoefficientTextSimilarity(double srensenDiceCoefficientTextSimilarity) {
        this.srensenDiceCoefficientTextSimilarity = srensenDiceCoefficientTextSimilarity;
    }
}
