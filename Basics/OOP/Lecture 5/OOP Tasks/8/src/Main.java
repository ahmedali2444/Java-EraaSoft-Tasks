public class Main {
    public static void main(String[] args) {
        Post socialPost = new Post(1, "Hello java spring", "oop.jpg");

        SocialMediaApi facebook = new Facebook();
        facebook.showPost(socialPost);

        SocialMediaApi linkedin = new LinkedIn();
        linkedin.showPost(socialPost);
    }
}
