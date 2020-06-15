package gradle.cucumber

import ar.unq.unqtrading.UnqTradingApplication
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.web.client.RestTemplate




@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [UnqTradingApplication::class], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SpringIntegrationTest {
    protected var restTemplate = RestTemplate()
    protected val DEFAULT_URL = "http://localhost:8080/api/venta"
    protected val USUARIO_URL = "http://localhost:8080/api/usuario"
}