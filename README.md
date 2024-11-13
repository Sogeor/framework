# Sogeor Framework

[![RELEASE](https://img.shields.io/github/v/release/sogeor/framework?style=for-the-badge)](https://github.com/sogeor/framework/releases/latest)
[![CI/CD](https://img.shields.io/github/actions/workflow/status/sogeor/framework/master.yml?style=for-the-badge&label=CI%2FCD)](https://github.com/sogeor/framework/actions/workflows/master.yml)

## Введение

Проект, то есть Sogeor Framework, состоит из нескольких взаимосвязанных подпроектов, называемых модулями. Каждый модуль
предназначен для решения конкретной проблемы, из-за чего он может нуждаться в других модулях. Проект устроен таким
образом, чтобы предоставить возможность использовать только необходимые модули, чтобы снизить количество зависимостей до
минимума.

## Структура проекта

## Модули

### `com.sogeor.throwable`

#### Проблема

Необходимо представлять программные сбои и неисправности в удобном виде, чтобы работать с ними везде абсолютно
одинаково, а также необходимо уйти от следующих стандартных понятий: `Throwable`, `Error`, `Exception`,
`RuntimeException` —, так как они не являются по-настоящему удобным видом представления программных сбоев и
неисправностей.

#### Решение

Из-за особенностей Java программные сбои и неисправности разделяются на две категории: проверяемые и непроверяемые. Для
каждого сбоя и неисправности существует представление, расширяющее одно из существующих:

| Название \ Категория | Проверяемый(-ая) | Непроверяемый(-ая) |
|:---------------------|:-----------------|:-------------------|
| Сбой                 | `CheckedFailure` | `UncheckedFailure` |
| Неисправность        | `CheckedFault`   | `UncheckedFault`   |

| Расширяющее представление | Расширяемое представление |
|:--------------------------|:--------------------------|
| `CheckedFailure`          | `Throwable`               |
| `UncheckedFailure`        | `Error`                   |
| `CheckedFault`            | `Exception`               |
| `UncheckedFault`          | `RuntimeException`        |

### `com.sogeor.validation`

#### Проблема

Необходимо валидировать аргументы методов и конструкторов, истинность и ложность условий, значения полей, анонимные
значения, а также значения переменных. Это необходимо для обеспечения дополнительной безопасности, исключения дальнейших
неконтролируемых программных сбоев и неисправностей, которые могут привести к аварийному завершению работы программы.
Существующие решения не подходят под все ситуации, например: `Objects::requireNonNull`, `Objects::requireNonNullElse`,
`Objects::requireNonNullElseGet`, `Objects::checkIndex`, `Objects::checkFromToIndex`,`Objects::checkFromIndexSize`.

#### Решение

Для каждой категории существует представление валидатора, а для каждой ситуации — представление программной
неисправности:

| Категория                         | Представление валидатора |
|:----------------------------------|:-------------------------|
| Аргументы методов и конструкторов | `ArgumentValidator`      |
| Истинность и ложность условий     | `ConditionValidator`     |
| Значения полей                    | `FieldValidator`         |
| Анонимные значения                | `ValueValidator`         |
| Значения переменных               | `VariableValidator`      |

| Ситуация                                                                        | Представление программной неисправности |
|:--------------------------------------------------------------------------------|:----------------------------------------|
| Невалидность аргумента метода или конструктора.                                 | `ArgumentValidationFault`               |
| Аргумент метода или конструктора должен быть нулевым, но таковым не является.   | `NonNullArgumentValidationFault`        |
| Аргумент метода или конструктора должен быть ненулевым, но таковым не является. | `NullArgumentValidationFault`           |
| Невалидность истинности и ложности условия.                                     | `ConditionValidationFault`              |
| Невалидность значения поля.                                                     | `FieldValidationFault`                  |
| Невалидность анонимного значения.                                               | `ValueValidationFault`                  |
| Невалидность значения переменной.                                               | `VariableValidationFault`               |
