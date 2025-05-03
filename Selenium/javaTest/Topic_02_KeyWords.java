package javaTest;

public abstract class Topic_02_KeyWords {
    static final String BROWSER = ""; // Hằng số
    String ram; // default
    private String rom; // Chỉ dùng trong class này
    protected String hdd; // Cho phép dùng bên ngoài class nhưng phải kết hợp với override
    public String ssd; // Dùng bất cứ đâu

    String serverName = "test";

    public void selectToServer() {
        String serverName = "host"; // biến serverName cục bộ
        this.serverName = ""; // dùng biến serverName bên ngoài
    }

    public void checkToRadio() throws InterruptedException {
        clickToElement();
    }

    // Non - abstract
    public void clickToElement() throws InterruptedException {
        // try - catch:
        // Nếu đúng sẽ vào try - không đúng vào catch
        // finally - Bắt buộc phải chạy qua
        Thread.sleep(5000);
        try {
            System.out.println("Click to element");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

    // Abstract
    public abstract void selectToCheckbox();

    public static void main(String[] args) {
        String browserName = "Chrome";
        if(browserName.equals("FireFox")) {

        } else {

        }

        switch (browserName) {
            case "Chrome":
            //
                break;
            case "FireFox":
                //
                break;
            default:
        }

        while (browserName.endsWith("Chrome")) {

        }

        do {

        } while (browserName.endsWith("Chrome"));

        String[] students = {"Nguyễn Văn Nam", "Lê Việt Dương", "Đinh Phương Văn"};
        for (int i = 0; i < students.length; i++) {

        }
    }
}
