public class Main {
    public static void main(String[] args) {
        Post firstPost = new Post(1, "Hello World!");
        firstPost.showPost();

        Post secondPost = new Post(2, "Check this out", "photo.jpg");
        secondPost.showPost();
    }
}
