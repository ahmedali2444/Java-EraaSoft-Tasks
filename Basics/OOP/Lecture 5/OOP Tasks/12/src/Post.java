public class Post {
    int postId;
    String postText;
    String imageName;

    public Post(int postId, String postText) {
        this.postId = postId;
        this.postText = postText;
    }

    public Post(int postId, String postText, String imageName) {
        this.postId = postId;
        this.postText = postText;
        this.imageName = imageName;
    }

    public void showPost() {
        System.out.println("Post ID: " + postId);
        System.out.println("Post Text: " + postText);
        if (imageName != null) {
            System.out.println("Post Image: " + imageName);
        }
    }
}
