package com.gui.constants;

/**
 * @author : peigui.huang
 * @Description: TODO
 * @date Date : 2020-01-04-15:03 15:03
 **/
public class SecurityPermissionConstants {


    public static class Activiti {

    }

    public static class Blog {
        public static final String QUERY_BLOG_LIST = "blog:bContent:bContent";
        public static final String ADD_BLOG = "blog:bContent:add";
        public static final String EDIT_BLOG = "blog:bContent:edit";
        public static final String REMOVE_BLOG = "blog:bContent:remove";
        public static final String BATCHREMOVE_BLOG = "blog:bContent:batchRemove";
    }

    public static class Common {
        public static final String QUERY_DICT_LIST = "common:dict:dict";
        public static final String ADD_DICT = "common:dict:add";
        public static final String EDIT_DICT = "common:dict:edit";
        public static final String REMOVE_DICT = "common:dict:remove";
        public static final String BATCHREMOVE_DICT = "common:dict:batchRemove";

        public static final String QUERY_FILE_LIST = "common:sysFile:sysFile";
        public static final String COMMON_INFO = "common:info";
        public static final String COMMON_SAVE = "common:save";
        public static final String COMMON_UPDATE = "common:update";
        public static final String COMMON_REMOVE = "common:remove";

    }

    public static class Oa {
        public static final String QUERY_NOTIFY_LIST = "oa:notify:notify";
        public static final String ADD_NOTIFY = "oa:notify:add";
        public static final String EDIT_NOTIFY = "oa:notify:edit";
        public static final String REMOVE_NOTIFY = "oa:notify:remove";
        public static final String BATCHREMOVE_NOTIFY = "oa:notify:batchRemove";

    }

    public static class System {
        public static final String QUERY_DEPT_LIST = "system:sysDept:sysDept";
        public static final String ADD_DEPT = "system:sysDept:add";
        public static final String EDIT_DEPT = "system:sysDept:edit";
        public static final String REMOVE_DEPT = "system:sysDept:remove";
        public static final String BATCHREMOVE_DEPT = "system:sysDept:batchRemove";

        public static final String QUERY_MENU_LIST = "sys:menu:menu";
        public static final String ADD_MENU = "sys:menu:add";
        public static final String EDIT_MENU = "sys:menu:edit";
        public static final String REMOVE_MENU = "sys:menu:remove";

        public static final String QUERY_ROLE_LIST = "sys:role:role";
        public static final String ADD_ROLE = "sys:role:add";
        public static final String EDIT_ROLE = "sys:role:edit";
        public static final String REMOVE_ROLE = "sys:role:remove";
        public static final String BATCHREMOVE_ROLE = "sys:role:batchRemove";

        public static final String QUERY_USER_LIST = "sys:user:user";
        public static final String ADD_USER = "sys:user:add";
        public static final String EDIT_USER = "sys:user:edit";
        public static final String REMOVE_USER = "sys:user:remove";
        public static final String BATCHREMOVE_USER = "sys:user:batchRemove";
        public static final String USER_RESETPWD ="sys:user:resetPwd";

    }
}
