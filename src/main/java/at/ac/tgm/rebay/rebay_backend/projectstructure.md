### Projektstruktur

    config/ – Enthält Sicherheits und Programm Konfigurationen.
    controllers/ – Enthält die REST-Controller, die HTTP-Anfragen verarbeiten.
    dtos/ - Enthält Data Transfer Objects, um Daten zwischen Backend und Frontend zu übertragen.
    exceptions/ – Enthält benutzerdefinierte Ausnahmen bzw. Fehlerverarbeitung, die im Projekt verwendet werden.
    models/ – Enthält die Datenentitäten, die die Tabellen in der Datenbank repräsentieren.
    repositories/ – Enthält Repositories zur Datenbankinteraktion (CRUD-Operationen).
    services/ – Enthält Logik und die Interaktion zwischen Controllern und Repositories.
    utils/ – Enthält allgemeine Hilfsklassen, die von mehreren Modulen genutzt werden können.