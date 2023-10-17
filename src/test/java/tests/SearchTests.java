package tests;


import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Check the Search ")
    @Description("Enter 'Java' to the search line and find article 'bject-oriented programming language'")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.BLOCKER)
//находим поле поиска, вводим там Java, смотрим выдачу поиска и ищем там Object-oriented programming language
    public void testSearch(){

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()){
        searchPageObject.setSearchSkipButton();}
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Check the cancel results of Search")
    @Description("Type 'Java' to the search line and cancel the search")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.BLOCKER)
//ищем поле ввода, пишем Java, очищаем ввод и проверяем что крестика нет на экране(что поиск закрыт)
    public void testCancelSearch(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelButton();
        searchPageObject.waitForCancelButtonToDissappear();

    }




    @Test
    @Feature(value = "Search")
    @DisplayName("Check the amount results of search")
    @Description("Type '{search_line}' to the search line and count results of search")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfNotEmptySearch(){

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        String search_line = "Linkin park Discography";
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    @Feature(value = "Search")
    @DisplayName("Check empty results of search")
    @Description("Type '{search_line}' to the search line and check empty result of search")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.MINOR)
    public void testAmountOfEmptySearch() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        String search_line = "hfgdzgfxhh";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultsOfSearch();

    }

    @Test
    @Features(value = {@Feature(value = "Search"),@Feature(value = "Article")})
    @DisplayName("Check title and description of some results after search")
    @Description("Type '{search_line}' to the search line and check the title and description of some results after search")
    @Step("Starting test testCheckTitleAndDescription")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCheckTitleAndDescription(){
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        String search_line = "Java";
       // String first_search_description = "Island in Indonesia";
        String second_search_title = "Java (programming language)";
        String second_search_description = "Object-oriented programming language";
        String third_search_title = "JavaScript";
        String third_search_description = "High-level programming language";
        searchPageObject.typeSearchLine(search_line);
       // searchPageObject.waitForElementByTitleAndDescription(search_line, first_search_description);
        searchPageObject.waitForElementByTitleAndDescription(second_search_title,second_search_description);
        searchPageObject.waitForElementByTitleAndDescription(third_search_title, third_search_description);
    }


}
