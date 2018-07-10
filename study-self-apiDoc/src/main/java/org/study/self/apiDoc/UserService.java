package org.study.self.apiDoc;

public interface UserService {
	
	/**
     * @api {get} /user/:id getName
     * @apiName getName
     * @apiGroup UserService
     *
     * @apiParam {Integer} id 用户ID.
     *
     * @apiSuccess {String} firstname Firstname of the User.
     */
    String getName(Integer id);
    
   /**
    * @apiDescription [desc]
    * @api {get} path [getUser]
    * @apiName getUser
    * @apiGroup UserService.java
    * @param id
    * @param tel
    * @return
    */
    /**
     * [简要描述]:
     * @param id
     * @param tel
     * @return
     * @author ggf
     * @date 2017年8月11日下午1:56:26
     */
	String getUser(Integer id, String tel);
}
