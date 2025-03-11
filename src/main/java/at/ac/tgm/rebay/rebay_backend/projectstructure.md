### Projektstruktur

    config/ – Enthält alle Konfigurationen (z.B. für Spring Security, Datenbank, externen APIs).
    controllers/ – Enthält die Controller, die HTTP-Anfragen verarbeiten.
    models/ – Enthält die Datenentitäten, die die Tabellen in der Datenbank repräsentieren sowie DTO (Data Transfer Objects) Klassen.
    repositories/ – Enthält Repositories zur Datenbankinteraktion (CRUD-Operationen).
    services/ – Enthält Business-Logik und die Interaktion zwischen Controllern und Repositories.
    util/ – Enthält allgemeine Hilfsklassen, die von mehreren Modulen genutzt werden können.