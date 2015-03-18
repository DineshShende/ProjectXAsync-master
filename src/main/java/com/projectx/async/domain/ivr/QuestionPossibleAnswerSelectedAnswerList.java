package com.projectx.async.domain.ivr;

import java.util.List;

public class QuestionPossibleAnswerSelectedAnswerList {

	List<QuestionPossibleAnswersSelectedAnswer> list;

	public QuestionPossibleAnswerSelectedAnswerList() {

	
	}

	public QuestionPossibleAnswerSelectedAnswerList(
			List<QuestionPossibleAnswersSelectedAnswer> list) {

		this.list = list;
	}

	public List<QuestionPossibleAnswersSelectedAnswer> getList() {
		return list;
	}

	public void setList(List<QuestionPossibleAnswersSelectedAnswer> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "QuestionPossibleAnswerSelectedAnswerList [list=" + list + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionPossibleAnswerSelectedAnswerList other = (QuestionPossibleAnswerSelectedAnswerList) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
	
	
	
	
}
