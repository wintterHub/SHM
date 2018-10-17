package com.zcj.shm.user.web.action.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

	/**
	 * ���ǽ����������Ķ���Ҫ��¼״̬���ܷ���
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// ���session�еġ�������Ӧ�ġ�ֵ��
		Object object = ActionContext.getContext().getSession().get("loginUser");
		// System.out.println(object == null ? "��¼session����" : "����");
		if (object == null) {
			// ���͵�¼���ڵ���ʾ
			Object action = invocation.getAction();
			if (action instanceof ActionSupport) {
				ActionSupport actionSupport = (ActionSupport) action;
				actionSupport.addActionError("��¼��֤���ڣ������µ�¼");
			}
			return "login";
		}
		return invocation.invoke();
	}

}
