package org.jeecg.modules.match.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MatchMemberVO implements Serializable{
	private static final long serialVersionUID = 1L;

	/**运动员id*/
	private String sportsmanId;
	/**对应的比赛id集合*/
	private List<String> matchIdList;
	public MatchMemberVO(String sportsmanId, List<String> matchIdList) {
		super();
		this.sportsmanId = sportsmanId;
		this.matchIdList = matchIdList;
	}


}
