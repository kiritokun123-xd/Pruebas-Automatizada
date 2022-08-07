@Juegos
Feature: Servicio de juegos
  @Scenario1:
  Scenario: Consulta del servidor
    Given que la aplicación esta operativo
    When muestra información de juegos de plataforma "pc" y "shooter"
    Then valida que la respuesta sea 200

  @Scenario2:
  Scenario: Consulta no se encuentra los juegos
    Given  que la aplicación esta operativo
    When se digita mas plataforma "ps" y categoria "shuter"
    Then valida que la respuesta sea 404

  @Scenario2:
  Scenario: Consulta error bad request
    Given  se abre el navegador
    When se digita mal la URL "https://www.freetogame.com/api/game%"
    Then valida que la respuesta sea 400

