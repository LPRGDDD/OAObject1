package Util;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class PageHibernateCallBack<T> implements HibernateCallback<List<T>> {

	private String hql;
	private Object[] params;//��������
	private int startIndex;//��ҳ
	private int pageSize;//ÿҳ��ʾ������
	
	

	public PageHibernateCallBack(String hql, Object[] params, int startIndex, int pageSize) {
		super();
		this.hql = hql;
		this.params = params;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}



	@Override
	public List<T> doInHibernate(Session session) throws HibernateException, SQLException {
		Query query=session.createQuery(hql);
		if(params!=null){
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		return query.list();
	}

}