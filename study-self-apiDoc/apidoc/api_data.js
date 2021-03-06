define({ "api": [
  {
    "description": "<p>[desc]</p>",
    "type": "get",
    "url": "path",
    "title": "[getUser]",
    "name": "getUser",
    "group": "UserService_java",
    "version": "0.0.0",
    "filename": "src/main/java/org/study/self/apiDoc/UserService.java",
    "groupTitle": "UserService_java"
  },
  {
    "type": "get",
    "url": "/user/:id",
    "title": "getName",
    "name": "getName",
    "group": "UserService",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Integer",
            "optional": false,
            "field": "id",
            "description": "<p>用户ID.</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "firstname",
            "description": "<p>Firstname of the User.</p>"
          }
        ]
      }
    },
    "version": "0.0.0",
    "filename": "src/main/java/org/study/self/apiDoc/UserService.java",
    "groupTitle": "UserService"
  }
] });
