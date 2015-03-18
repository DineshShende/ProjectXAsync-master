package com.projectx.async.repository.ivr;

import java.util.List;

import com.projectx.async.domain.ivr.QuestionPossibleAnswersSelectedAnswer;




public interface QuestionPossibleAnswersSelectedAnswerRepositoty {

	QuestionPossibleAnswersSelectedAnswer save(QuestionPossibleAnswersSelectedAnswer entity);
	
	List<QuestionPossibleAnswersSelectedAnswer> getAll();
	
	QuestionPossibleAnswersSelectedAnswer getQuestionById(Long questionId);
	
}
