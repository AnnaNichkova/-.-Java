package lessons;

public class Locators {
    public String enterButton = "//*[@href='/login' and contains (text(), 'Войти')]";

    private String loginEmail = "//*[@name='user[email]']";
    private String loginPassword = "//*[@name='user[password]']";
    private String loginEnterButton = "//*[@id='new_user']//*[@value='Войти']";

    private String coursesLink = "//*[@id='nav']//*[@href='/courses']";
    private String qaEngineerLink = "//*[@data-ga-list='cour_new']//*[@href='/professions/qa_engineer']";
    private String slotsCount = "//*[@id='anchor-checkout']/parent::*//*[@data-cookie='qa_engineer_free_places']";
    private String successStoriesVideos = "//*[@id='anchor-success-stories']//*[contains(@id, 'video')]//video";

    private String programSizeSubtitle = "//*[@id='anchor-program]/parent::*//*[contains(@class, 'subtitle') and contains(text(), 'практ') and contains(text(), 'курс')]";
    private String programStages = "//*[@id='anchor-program]/parent::*//*[contains(@class, 'item-title')]";
    private String fullProgram = "//*[@href='#detailed-program']";
    private String attestation = "//*[contains(text(), 'Результат подтверждают')]/parent::*//*[text()='Свидетельство']";
    private String callBackButtonId = "//*[@id='lb_button-call']";
    private String faqArrows = "//*[contains(text(), 'Часто задаваемые вопросы')]/parent::*//*[contains(@class, 'arrow-wrapper')]";

}
