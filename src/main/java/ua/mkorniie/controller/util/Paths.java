package ua.mkorniie.controller.util;

//TODO: finish transferring addresses here
//TODO: add servlet paths here

public enum Paths {
    ACCESS_ERROR_PAGE("templates/no-rights.jsp"),
    GENERAL_ERROR("templates/general-error.jsp"),
    LOGIN("templates/login.jsp"),
    REGISTER("templates/register-form.jsp"),
    APPROVE_ROOM_REQ("templates/admin/approve-request.jsp"),
    ADMIN_MAIN("templates/admin/success_admin.jsp"),
    USER_MAIN("templates/user/user-main.jsp");

    private String url;

    Paths(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
