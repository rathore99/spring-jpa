## Spring Boot Testing with JUnit 5 and Mockito

This project contains comprehensive examples of testing Spring Boot REST APIs and components with JUnit 5 and Mockito. Browse the tests under `src/test/java` and use this guide as a quick reference to key annotations and patterns.

### JUnit 5 core annotations
- **@Test**: Marks a test method. Example: `AdvancedJUnitTest#addition`.
- **@DisplayName**: Custom name for tests or containers. See nested classes.
- **@BeforeAll / @AfterAll**: Run once per class, must be static. See `AdvancedJUnitTest`.
- **@BeforeEach / @AfterEach**: Run before/after each test. See `AdvancedJUnitTest`.
- **@Nested**: Group related tests; inherits outer lifecycle. See `AdvancedJUnitTest.MathOps`.
- **@RepeatedTest(N)**: Runs the same test N times. See `AdvancedJUnitTest#repeated`.
- **@Timeout**: Fails if test exceeds duration. See `AdvancedJUnitTest#timeout`.
- **Assumptions**: `assumeTrue/assumeFalse` to skip when preconditions fail. See `AdvancedJUnitTest#assumption`.
- **Parameterized tests**: `@ParameterizedTest` with sources like `@ValueSource`, `@CsvSource`. See `AdvancedJUnitTest#param_value` and `#param_csv`.

### Mockito core annotations and usage
- **@ExtendWith(MockitoExtension.class)**: Enables Mockito in JUnit 5 tests. See service tests.
- **@Mock**: Creates a mock; behavior is programmed via `when(...).thenReturn(...)` or BDD style `given(...).willReturn(...)`.
- **@InjectMocks**: Creates class under test and injects mocks into it. See `ProductServiceTest`, `UserServiceTest`.
- **@Captor**: Type-safe argument captor to assert interactions. See `AdvancedMockitoTest#captor_and_spy_demo`.
- **@Spy**: Partial mock that calls real methods unless stubbed. See `AdvancedMockitoTest`.
- **Answers**: `thenAnswer(invocation -> ...)` for dynamic stubbing. See `AdvancedMockitoTest#answers_and_verification_modes` and service tests.
- **Verification**: `verify(mock).method()`, modes like `times(n)`, `never()`, `atLeastOnce()`, plus `verifyNoMoreInteractions(...)`.

### Spring Boot testing annotations
- **@SpringBootTest**: Boots entire context for full integration tests. See `SpringContextTest`.
- **@WebMvcTest(Controller.class)**: MVC slice test that loads controller + MVC infra; collaborators are mocked with `@MockBean`. See `ProductControllerTest`, `UserControllerTest`.
- **@DataJpaTest**: JPA slice with in-memory database, scanning `@Entity` and Spring Data repositories. See `ProductRepositoryTest`.
- **@MockBean**: Adds a Spring-managed mock to the context (different from `@Mock`). Used in slice tests to replace collaborators.

### REST testing patterns (MockMvc)
- Build requests with `get/post/put/delete("/path")`, set `.contentType(MediaType.APPLICATION_JSON)`, and pass JSON body.
- Assert with `status().isOk()`, `jsonPath("$.field")`, `content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)`.
- Examples: `ProductControllerTest#create_returnsOk`, `UserControllerTest#getUser_returnsOk`.

### Service tests patterns
- Use `@ExtendWith(MockitoExtension.class)`, `@Mock` repositories and `@InjectMocks` services.
- Stub behavior with `when(...).thenReturn(...)` or `thenAnswer(...)`.
- Verify side effects and interactions with `verify(...)` and `ArgumentCaptor`.
- Examples: `ProductServiceTest`, `UserServiceTest`.

### Repository tests patterns
- `@DataJpaTest` provides transactional tests that roll back by default.
- Persist with `repository.save(entity)` then assert retrieval via query methods.
- Example: `ProductRepositoryTest`.

### Tips and gotchas
- Prefer constructor injection in production code to ease unit testing.
- In MVC tests, always mock service layer with `@MockBean` to keep the slice narrow.
- Keep unit tests fast by avoiding full context unless you need it; reserve `@SpringBootTest` for cross-cutting concerns.

### Where to look in this repo
- JUnit features: `src/test/java/com/rr/example/spring_jpa/junit/AdvancedJUnitTest.java`
- Mockito features: `src/test/java/com/rr/example/spring_jpa/mockito/AdvancedMockitoTest.java`
- Service unit tests: `src/test/java/com/rr/example/spring_jpa/service/*Test.java`
- Controller slice tests: `src/test/java/com/rr/example/spring_jpa/controller/*Test.java`
- Repository tests: `src/test/java/com/rr/example/spring_jpa/repository/*Test.java`
- Full Spring context: `src/test/java/com/rr/example/spring_jpa/SpringContextTest.java`


