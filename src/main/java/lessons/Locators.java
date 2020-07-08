package lessons;

public class Locators {
    public String enterButton = "//*[@href='/login' and contains (text(), 'Войти')]";

    public String loginEmail = "//*[@name='user[email]']";
    public String loginPassword = "//*[@name='user[password]']";
    public String loginEnterButton = "//*[@id='new_user']//*[@value='Войти']";

    public String coursesLink = "//*[@id='nav']//*[@href='/courses']";
    public String qaEngineerLink = "//*[@data-ga-list='cour_new']//*[@href='/professions/qa_engineer']";
    public String slotsCount = "//*[@id='anchor-checkout']/parent::*//*[@data-cookie='qa_engineer_free_places']";
    public String successStoriesVideos = "//*[@id='anchor-success-stories']//*[contains(@id, 'video')]//video";

    public String programSizeSubtitle = "//*[@id='anchor-program]/parent::*//*[contains(@class, 'subtitle') and contains(text(), 'практ') and contains(text(), 'курс')]";
    public String programStages = "//*[@id='anchor-program]/parent::*//*[contains(@class, 'item-title')]";
    public String fullProgram = "//*[@href='#detailed-program']";
    public String attestation = "//*[contains(text(), 'Результат подтверждают')]/parent::*//*[text()='Свидетельство']";
    public String callBackButtonId = "//*[@id='lb_button-call']";
    public String faqArrows = "//*[contains(text(), 'Часто задаваемые вопросы')]/parent::*//*[contains(@class, 'arrow-wrapper')]";

}
