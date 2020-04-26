package com.chat.qs.word.qa;

import com.chat.qs.utils.SocketMessage;
//import org.apdplat.qa.SharedQuestionAnsweringSystem;
//import org.apdplat.qa.model.CandidateAnswer;
//import org.apdplat.qa.model.Question;

import java.util.List;

/**
 * Created by Administrator on 2020/4/16 0016.
 */
public class QuestionAnswerHelper {

    private volatile static QuestionAnswerHelper instance = null;

    private QuestionAnswerHelper(){}

    public static QuestionAnswerHelper getInstance()   {
        if (instance == null)  {
            synchronized (QuestionAnswerHelper.class) {
                if (instance== null)  {
                    instance= new QuestionAnswerHelper();
                }
            }
        }
        return instance;
    }

    /*public void initQuestionSystem(){
        String questionStr = "APDPlat的作者是谁？";
        Question question = SharedQuestionAnsweringSystem.getInstance().answerQuestion(questionStr);
        if (question != null) {
            List<CandidateAnswer> candidateAnswers = question.getAllCandidateAnswer();
            int i=1;
            for(CandidateAnswer candidateAnswer : candidateAnswers){
                System.out.println((i++)+"、"+candidateAnswer.getAnswer()+":"+candidateAnswer.getScore());
            }
        }
    }

    public SocketMessage questionSystem(String questionStr, int topN){
        Question question = null;
        List<CandidateAnswer> candidateAnswers = null;
        if (questionStr != null && questionStr.trim().length() > 3) {
            question = SharedQuestionAnsweringSystem.getInstance().answerQuestion(questionStr);
            if (question != null) {
                candidateAnswers = question.getAllCandidateAnswer();
                int i=1;
                for(CandidateAnswer candidateAnswer : candidateAnswers){
                    System.out.println((i++)+"、"+candidateAnswer.getAnswer()+":"+candidateAnswer.getScore());
                }
            }
        }
        System.out.println("问题："+questionStr);
        String json = JsonGenerator.generate(candidateAnswers, topN);
        System.out.println("答案："+json);

        if(candidateAnswers!=null && candidateAnswers.size()>0){
            return SocketMessage.text(candidateAnswers.get(topN).getAnswer());
        }
        return SocketMessage.fail("未找到问题说明！");
    }*/
}
