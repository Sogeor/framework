# Sogeor Framework

[![RELEASE](https://img.shields.io/github/v/release/sogeor/framework?style=for-the-badge)](https://github.com/sogeor/framework/releases/latest)
[![CI/CD](https://img.shields.io/github/actions/workflow/status/sogeor/framework/master.yml?style=for-the-badge&label=CI%2FCD)](https://github.com/sogeor/framework/actions/workflows/master.yml)

## Введение

Представляет собой модульный полнофункциональный фреймворк, каждый модуль которого стремится к совершенству и
минимальному объёму зависимостей, а документация написана полностью на русском языке и также стремится к совершенству.

## Модули

### `com.sogeor.framework.annotation`

#### Описание

Предоставляет фундаментальные аннотации, на которых основываются все модули проекта.

### `com.sogeor.framework.collection`

#### Описание

Предоставляет фундаментальные коллекции, на которых основывается большинство модулей проекта.

#### Зависимости

| Идентификатор                     |
|:----------------------------------|
| `com.sogeor.framework.annotation` |
| `com.sogeor.framework.common`     |
| `com.sogeor.framework.function`   |
| `com.sogeor.framework.throwable`  |
| `com.sogeor.framework.validation` |

### `com.sogeor.framework.common`

#### Описание

Предоставляет фундаментальные инструменты, на которых основывается большинство модулей проекта.

#### Зависимости

| Идентификатор                     |
|:----------------------------------|
| `com.sogeor.framework.annotation` |
| `com.sogeor.framework.function`   |
| `com.sogeor.framework.throwable`  |
| `com.sogeor.framework.validation` |

### `com.sogeor.framework.function`

#### Описание

Предоставляет функциональные инструменты, на которых основывается большинство модулей проекта.

#### Зависимости

| Идентификатор                     |
|:----------------------------------|
| `com.sogeor.framework.annotation` |
| `com.sogeor.framework.throwable`  |
| `com.sogeor.framework.validation` |

### `com.sogeor.framework.throwable`

#### Описание

Предоставляет фундаментальные программные сбои и неисправности, на которых основывается большинство модулей проекта.

#### Зависимости

| Идентификатор                     |
|:----------------------------------|
| `com.sogeor.framework.annotation` |

### `com.sogeor.framework.validation`

#### Описание

Предоставляет инструменты для валидации объектов и значений, на которых основывается большинство модулей проекта.

#### Зависимости

| Идентификатор                     |
|:----------------------------------|
| `com.sogeor.framework.annotation` |
| `com.sogeor.framework.throwable`  |
