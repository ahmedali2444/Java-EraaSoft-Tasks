public class Facebook implements SocialMediaApi {
    public void showPost(Post postInfo) {
        System.out.println("Current platform: Facebook");
        System.out.println("Post ID: " + postInfo.postId);
        System.out.println("Post Text: " + postInfo.postText);
        System.out.println("Post Image: " + postInfo.imageName);
    }
}
