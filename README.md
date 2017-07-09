# SpringSimpleTransactionExample
This is just my back up, show simple processing result for spring default tansaction manger(org.springframework.jdbc.datasource.DataSourceTransactionManager)

If no declared @Transactional occurred, the method will run in non-tx-manager, each dml operation will commit
immediately, any throwable later will not cause roll back.If this method invoke another sub-method annotated with
@Transactional, the sub-method wiil run in tx-manager, each dml in sub-method will not commit until the sub-method finish
running.
For default @Transactional, only type of RunTimeException will cause roll back.

#### Condition one:parent method in class1, child method in class2, tx-mode:proxy
| parent method | child method | simple description |
| ------ | ------ | ------ |
| @Transactional  | @Transactional  | same tx |
| @Transactional  | @Transactional(Propagation.REQUIRES_NEW)  | 2 different tx, each tx will do its own commit |
| @Transactional  | None  | child method has no tx, each dml in it will commit immediately |
| None  | @Transactional  | parent method has no tx, each dml in it will commit immediately |

#### Condition two:parent method and child method both in class1, tx-mode:proxy
| parent method | child method | simple description |
| ------ | ------ | ------ |
| @Transactional  | @Transactional  | same tx |
| @Transactional  | @Transactional(Propagation.REQUIRES_NEW)  | same tx |
| @Transactional  | None  | same tx |
| None  | @Transactional  | no tx for both method, each dml in it will commit immediately |

#### If you want condition two behaves like condition one, just using tx-mode:aspectj for tx-manager configuration, aspectj can also intercept private method for tx-manager.



