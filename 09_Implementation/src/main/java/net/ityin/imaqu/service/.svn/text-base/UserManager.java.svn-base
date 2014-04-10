/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.ityin.imaqu.model.Answer;
import net.ityin.imaqu.model.Question;
import net.ityin.imaqu.model.User;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public interface UserManager extends GenericManager<User, String> {
	public boolean checkNicknameExistence(String nickname);

	public User createUser(User user);

	public User autoLogin(String username, String password,
			HttpServletRequest request);

	public List<User> getTopAsker(Date date, int maxResults);

	public List<User> getTopReplier(Date date, int maxResults);

	public void setAuthentication(User user);

	public List<Question> getQuestionsByUser(User user, Byte status,
			Boolean recommended, int firstResult, int maxResults);

	public List<Answer> getAnswersByUser(User user, Byte status,
			int firstResult, int maxResults);

	public List<User> getTopUsers(int orderProperty, int maxResults);

	public int getUserOrder(User u, int orderProperty);

	public List<User> getUsersByCondition(User user, String order,
			int firstResult, int maxResults);

	public void switchUserState(String[] ids, Date time, boolean active);

	public void kickUsers(String[] userIds);

	public void deleteUsers(String[] ids, boolean cascadeQ, boolean cascadeA);

	public void switchRole(String[] ids);
}