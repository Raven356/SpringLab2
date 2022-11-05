package configuration;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import repository.CategoryRepository;
import repository.UserRepository;
import services.CategoriesService;
import services.UserService;

@org.springframework.context.annotation.Configuration
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
}
