// Code generated by gorm.io/gen. DO NOT EDIT.
// Code generated by gorm.io/gen. DO NOT EDIT.
// Code generated by gorm.io/gen. DO NOT EDIT.

package query

import (
	"context"
	"database/sql"

	"gorm.io/gorm"

	"gorm.io/gen"

	"gorm.io/plugin/dbresolver"
)

var (
	Q            = new(Query)
	CharactersDO *charactersDO
	SubscriberDO *subscriberDO
	UsersDO      *usersDO
)

func SetDefault(db *gorm.DB, opts ...gen.DOOption) {
	*Q = *Use(db, opts...)
	CharactersDO = &Q.CharactersDO
	SubscriberDO = &Q.SubscriberDO
	UsersDO = &Q.UsersDO
}

func Use(db *gorm.DB, opts ...gen.DOOption) *Query {
	return &Query{
		db:           db,
		CharactersDO: newCharactersDO(db, opts...),
		SubscriberDO: newSubscriberDO(db, opts...),
		UsersDO:      newUsersDO(db, opts...),
	}
}

type Query struct {
	db *gorm.DB

	CharactersDO charactersDO
	SubscriberDO subscriberDO
	UsersDO      usersDO
}

func (q *Query) Available() bool { return q.db != nil }

func (q *Query) clone(db *gorm.DB) *Query {
	return &Query{
		db:           db,
		CharactersDO: q.CharactersDO.clone(db),
		SubscriberDO: q.SubscriberDO.clone(db),
		UsersDO:      q.UsersDO.clone(db),
	}
}

func (q *Query) ReadDB() *Query {
	return q.ReplaceDB(q.db.Clauses(dbresolver.Read))
}

func (q *Query) WriteDB() *Query {
	return q.ReplaceDB(q.db.Clauses(dbresolver.Write))
}

func (q *Query) ReplaceDB(db *gorm.DB) *Query {
	return &Query{
		db:           db,
		CharactersDO: q.CharactersDO.replaceDB(db),
		SubscriberDO: q.SubscriberDO.replaceDB(db),
		UsersDO:      q.UsersDO.replaceDB(db),
	}
}

type queryCtx struct {
	CharactersDO ICharactersDODo
	SubscriberDO ISubscriberDODo
	UsersDO      IUsersDODo
}

func (q *Query) WithContext(ctx context.Context) *queryCtx {
	return &queryCtx{
		CharactersDO: q.CharactersDO.WithContext(ctx),
		SubscriberDO: q.SubscriberDO.WithContext(ctx),
		UsersDO:      q.UsersDO.WithContext(ctx),
	}
}

func (q *Query) Transaction(fc func(tx *Query) error, opts ...*sql.TxOptions) error {
	return q.db.Transaction(func(tx *gorm.DB) error { return fc(q.clone(tx)) }, opts...)
}

func (q *Query) Begin(opts ...*sql.TxOptions) *QueryTx {
	tx := q.db.Begin(opts...)
	return &QueryTx{Query: q.clone(tx), Error: tx.Error}
}

type QueryTx struct {
	*Query
	Error error
}

func (q *QueryTx) Commit() error {
	return q.db.Commit().Error
}

func (q *QueryTx) Rollback() error {
	return q.db.Rollback().Error
}

func (q *QueryTx) SavePoint(name string) error {
	return q.db.SavePoint(name).Error
}

func (q *QueryTx) RollbackTo(name string) error {
	return q.db.RollbackTo(name).Error
}