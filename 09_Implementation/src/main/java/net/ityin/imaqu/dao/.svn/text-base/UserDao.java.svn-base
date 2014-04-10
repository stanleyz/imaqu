/**
 * Xopen Ltd. All Rights Reserved.
 */
package net.ityin.imaqu.dao;

import java.util.Date;
import java.util.List;

import net.ityin.imaqu.model.Answer;
import net.ityin.imaqu.model.Question;
import net.ityin.imaqu.model.User;

/**
 * @author <a href="phinux.zhang@ityin.net">Phinux Zhang</a>
 * 
 */
public interface UserDao extends GenericDao<User, String> {
	public User findByNickname(String nickname);

	public List<User> getTopAsker(Date date, int maxResults);

	public List<User> getTopReplier(Date date, int maxResults);

	public List<Question> getQuestionsByUser(User user, Byte status,
			Boolean recommended, int firstResult, int maxResults);

	public List<Answer> getAnswersByUser(User user, Byte status,
			int firstResult, int maxResults);

	public List<User> getTopUsers(int orderProperty, int maxResults);

	public int getUserOrder(User u, int orderProperty);

	public List<User> getUsersByCondition(User user, String order,
			int firstResult, int maxResults);

	public void switchUserState(User user, Date time, boolean active);
}
