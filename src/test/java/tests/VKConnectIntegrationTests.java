package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import models.Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pageobjects.ConnectPage;
import pageobjects.IntegrationPage;
import testdata.ServiceData;

import java.util.stream.Stream;

@DisplayName("I&T тесты VKConnect")
public class VKConnectIntegrationTests extends TestBase {

    static IntegrationPage integrationPage = new IntegrationPage();
    static ConnectPage connectPage = new ConnectPage();

    static Stream<Arguments> serviceProvider() {
        return Stream.of(
                Arguments.of(new ServiceData().VKCOMBO),
                Arguments.of(new ServiceData().VKRABOTA),
                Arguments.of(new ServiceData().MAILRU),
                Arguments.of(new ServiceData().OK)
        );
    }

    @ParameterizedTest(name = "Проверка VKConnect на внешнем сервисе")
    @MethodSource("serviceProvider")
    @Feature("Интеграции VKConnect")
    @Severity(SeverityLevel.NORMAL)
    @Story("Личные данные")
    public void checkConnect(Service service) {
            integrationPage
                    .openIntegrationServicePage(service.getUrl())
                    .clickVKCButton(service.getXpath());

            connectPage
                    .switchPage(1)
                    .chekQuickAuth()
                    .closePage()
                    .switchPage(0);
    }

}
