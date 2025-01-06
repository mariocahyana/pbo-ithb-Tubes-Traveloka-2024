package Model.Model_class;

public class Reply {
    private int reviewID;
    private int userID;
    private String replyAdmin;

    public Reply(int reviewID, int userID, String replyAdmin) {
        this.reviewID = reviewID;
        this.userID = userID;
        this.replyAdmin = replyAdmin;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getReplyAdmin() {
        return replyAdmin;
    }

    public void setReplyAdmin(String replyAdmin) {
        this.replyAdmin = replyAdmin;
    }
}
