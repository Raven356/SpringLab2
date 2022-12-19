package controller.configuration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.CategoryRepository;
import repository.RestCategoryRepository;
import repository.RestGoodsRepository;
import repository.UserRepository;
import services.CategoriesDbService;
import services.CategoriesService;
import services.UserService;

@org.springframework.context.annotation.Configuration
@EnableJpaRepositories("repository")
@EntityScan("models")
public class Configuration
{
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserRepository userRepository()
    {
        return new UserRepository();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CategoryRepository categoryRepository()
    {
        return new CategoryRepository();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public CategoriesService categoryService()
    {
        return new CategoriesService();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public UserService userService()
    {
        return new UserService();
    }

    @Bean
    public CategoriesDbService categoriesDbService(){
        return new CategoriesDbService();
    }

}
