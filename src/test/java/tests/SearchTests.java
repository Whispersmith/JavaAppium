package tests;


import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
//находим поле поиска, вводим там Java, смотрим выдачу поиска и ищем там Object-oriented programming language
    public void testSearch(){



     /*   MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find Element Search",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_display']//*[@text = 'Object-oriented programming language']"),
                "Cannot find Object-oriented programming language in list",
                15
        );*/

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()){
        searchPageObject.setSearchSkipButton();}
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
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
    public void testAmountOfNotEmptySearch(){

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.setSearchSkipButton();
        searchPageObject.initSearchInput();
        String search_line = "Linkin park Discography";
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
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
