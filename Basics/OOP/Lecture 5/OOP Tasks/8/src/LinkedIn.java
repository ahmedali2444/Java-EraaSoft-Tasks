public class LinkedIn implements SocialMediaApi {
    public void showPost(Post postInfo) {
        System.out.println("Current platform: LinkedIn");
        System.out.println("Post ID: " + postInfo.postId);
        System.out.println("Post Text: " + postInfo.postText);
        System.out.println("Post Image: " + postInfo.imageName);
    }
}
