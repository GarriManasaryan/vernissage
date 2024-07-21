package test;

import com.handicraft.vernissage.domain.product.category.CategoryRepo;
import com.handicraft.vernissage.domain.product.feature.FeatureDiscriminator;
import com.handicraft.vernissage.domain.product.feature.FeatureText;
import com.handicraft.vernissage.port.adapters.persistence.handlers.JdbcPostgresExecuterRepo;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static com.handicraft.vernissage.port.adapters.persistence.models.CategorySQLModel.table;
import static com.handicraft.vernissage.port.adapters.persistence.product.PostgresqlCategory.asCategoryRowMapping;

@SpringBootTest
public class SomeTests {

    @Test
    public void test(){
//        var f = new FeatureText(
//                "id",
//                "id",
//                Optional.ofNullable("id"),
//                Optional.ofNullable("id"),
//                List.of(),
//
//        );

    }

}
