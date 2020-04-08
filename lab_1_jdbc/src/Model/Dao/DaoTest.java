package Model.Dao;

import Model.HighLevel.Answer;
import Model.HighLevel.Question;
import Model.HighLevel.Test;
import Model.Connector.ConnectorException;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoTest extends Dao{

    final String TABLE_NAME = "Questions";
    final String statementString = "SELECT id, question, type FROM " + TABLE_NAME;
    final private String insertStatement = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?)";

    public DaoTest() throws DaoException {
        super();
    }

    public Map<Integer, Test> getTests() throws DaoException {
        Map<Integer, Test> testsMap = new HashMap<>();
        try {
            Connection connection = connector.getConnection();

            DaoAnswer daoAnswer = new DaoAnswer();

            Statement statement = connection.createStatement();
            ResultSet questionsSet =  statement.executeQuery(statementString);
            while(questionsSet.next()){
                int id = questionsSet.getInt("id");
                String questionString = questionsSet.getString("question");
                int type = questionsSet.getInt("type");
                Question question = new Question(id, questionString, type);

                List<Answer> answerList = daoAnswer.getAnswers(id);
                question.setAnswers(answerList);

                if (testsMap.containsKey(type)){
                    Test test = testsMap.get(type);
                    test.addQuestion(question);
                }
                else {
                    Test test = new Test(type);
                    test.addQuestion(question);
                    testsMap.put(type, test);
                }
            }
            connector.closeConnection();
        } catch (ConnectorException e) {
            throw new DaoException("Connect to database failed.", e);
        } catch (SQLException e){
            throw new DaoException("Failed with statement " + statementString + ".", e);
        }
        return testsMap;
    }

    /*
    insert all questions in Test and answers to them;
     */
    public boolean insertTest(Test test) throws DaoException {
        boolean isExecuted = false;
        DaoQuestions daoQuestions = new DaoQuestions();
        DaoAnswer daoAnswer = new DaoAnswer();
        for (Question q: test.getQuestions()) {
            daoQuestions.insertQuestion(q);
            List<Answer> answers = q.getAnswers();
            for (Answer a: answers){
                daoAnswer.insertAnswer(a, q.getId());
            }
        }
        return isExecuted;
    }
}
