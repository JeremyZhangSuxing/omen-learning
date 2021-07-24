package com.omen.learning.sample.mybatis.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DispatchBillInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer offset;

    protected Integer rows;

    public DispatchBillInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public DispatchBillInfoExample orderBy(String orderByClause) {
        this.setOrderByClause(orderByClause);
        return this;
    }

    public DispatchBillInfoExample orderBy(String ... orderByClauses) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < orderByClauses.length; i++) {
            sb.append(orderByClauses[i]);
            if (i < orderByClauses.length - 1) {
                sb.append(" , ");
            }
        }
        this.setOrderByClause(sb.toString());
        return this;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria(this);
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
        rows = null;
        offset = null;
    }

    public static Criteria newAndCreateCriteria() {
        DispatchBillInfoExample example = new DispatchBillInfoExample();
        return example.createCriteria();
    }

    public DispatchBillInfoExample when(boolean condition, IExampleWhen then) {
        if (condition) {
            then.example(this);
        }
        return this;
    }

    public DispatchBillInfoExample when(boolean condition, IExampleWhen then, IExampleWhen otherwise) {
        if (condition) {
            then.example(this);
        } else {
            otherwise.example(this);
        }
        return this;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getRows() {
        return this.rows;
    }

    public DispatchBillInfoExample limit(Integer rows) {
        this.rows = rows;
        return this;
    }

    public DispatchBillInfoExample limit(Integer offset, Integer rows) {
        this.offset = offset;
        this.rows = rows;
        return this;
    }

    public DispatchBillInfoExample page(Integer page, Integer pageSize) {
        this.offset = page * pageSize;
        this.rows = pageSize;
        return this;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBillNoIsNull() {
            addCriterion("bill_no is null");
            return (Criteria) this;
        }

        public Criteria andBillNoIsNotNull() {
            addCriterion("bill_no is not null");
            return (Criteria) this;
        }

        public Criteria andBillNoEqualTo(String value) {
            addCriterion("bill_no =", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotEqualTo(String value) {
            addCriterion("bill_no <>", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoGreaterThan(String value) {
            addCriterion("bill_no >", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoGreaterThanOrEqualTo(String value) {
            addCriterion("bill_no >=", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoLessThan(String value) {
            addCriterion("bill_no <", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoLessThanOrEqualTo(String value) {
            addCriterion("bill_no <=", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoLike(String value) {
            addCriterion("bill_no like", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotLike(String value) {
            addCriterion("bill_no not like", value, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoIn(List<String> values) {
            addCriterion("bill_no in", values, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotIn(List<String> values) {
            addCriterion("bill_no not in", values, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoBetween(String value1, String value2) {
            addCriterion("bill_no between", value1, value2, "billNo");
            return (Criteria) this;
        }

        public Criteria andBillNoNotBetween(String value1, String value2) {
            addCriterion("bill_no not between", value1, value2, "billNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoIsNull() {
            addCriterion("biz_flow_no is null");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoIsNotNull() {
            addCriterion("biz_flow_no is not null");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoEqualTo(String value) {
            addCriterion("biz_flow_no =", value, "bizFlowNo");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoNotEqualTo(String value) {
            addCriterion("biz_flow_no <>", value, "bizFlowNo");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoGreaterThan(String value) {
            addCriterion("biz_flow_no >", value, "bizFlowNo");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoGreaterThanOrEqualTo(String value) {
            addCriterion("biz_flow_no >=", value, "bizFlowNo");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoLessThan(String value) {
            addCriterion("biz_flow_no <", value, "bizFlowNo");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoLessThanOrEqualTo(String value) {
            addCriterion("biz_flow_no <=", value, "bizFlowNo");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoLike(String value) {
            addCriterion("biz_flow_no like", value, "bizFlowNo");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoNotLike(String value) {
            addCriterion("biz_flow_no not like", value, "bizFlowNo");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoIn(List<String> values) {
            addCriterion("biz_flow_no in", values, "bizFlowNo");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoNotIn(List<String> values) {
            addCriterion("biz_flow_no not in", values, "bizFlowNo");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoBetween(String value1, String value2) {
            addCriterion("biz_flow_no between", value1, value2, "bizFlowNo");
            return (Criteria) this;
        }

        public Criteria andBizFlowNoNotBetween(String value1, String value2) {
            addCriterion("biz_flow_no not between", value1, value2, "bizFlowNo");
            return (Criteria) this;
        }

        public Criteria andBizTypeIsNull() {
            addCriterion("biz_type is null");
            return (Criteria) this;
        }

        public Criteria andBizTypeIsNotNull() {
            addCriterion("biz_type is not null");
            return (Criteria) this;
        }

        public Criteria andBizTypeEqualTo(String value) {
            addCriterion("biz_type =", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotEqualTo(String value) {
            addCriterion("biz_type <>", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeGreaterThan(String value) {
            addCriterion("biz_type >", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeGreaterThanOrEqualTo(String value) {
            addCriterion("biz_type >=", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeLessThan(String value) {
            addCriterion("biz_type <", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeLessThanOrEqualTo(String value) {
            addCriterion("biz_type <=", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeLike(String value) {
            addCriterion("biz_type like", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotLike(String value) {
            addCriterion("biz_type not like", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeIn(List<String> values) {
            addCriterion("biz_type in", values, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotIn(List<String> values) {
            addCriterion("biz_type not in", values, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeBetween(String value1, String value2) {
            addCriterion("biz_type between", value1, value2, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotBetween(String value1, String value2) {
            addCriterion("biz_type not between", value1, value2, "bizType");
            return (Criteria) this;
        }

        public Criteria andBillSourceIsNull() {
            addCriterion("bill_source is null");
            return (Criteria) this;
        }

        public Criteria andBillSourceIsNotNull() {
            addCriterion("bill_source is not null");
            return (Criteria) this;
        }

        public Criteria andBillSourceEqualTo(String value) {
            addCriterion("bill_source =", value, "billSource");
            return (Criteria) this;
        }

        public Criteria andBillSourceNotEqualTo(String value) {
            addCriterion("bill_source <>", value, "billSource");
            return (Criteria) this;
        }

        public Criteria andBillSourceGreaterThan(String value) {
            addCriterion("bill_source >", value, "billSource");
            return (Criteria) this;
        }

        public Criteria andBillSourceGreaterThanOrEqualTo(String value) {
            addCriterion("bill_source >=", value, "billSource");
            return (Criteria) this;
        }

        public Criteria andBillSourceLessThan(String value) {
            addCriterion("bill_source <", value, "billSource");
            return (Criteria) this;
        }

        public Criteria andBillSourceLessThanOrEqualTo(String value) {
            addCriterion("bill_source <=", value, "billSource");
            return (Criteria) this;
        }

        public Criteria andBillSourceLike(String value) {
            addCriterion("bill_source like", value, "billSource");
            return (Criteria) this;
        }

        public Criteria andBillSourceNotLike(String value) {
            addCriterion("bill_source not like", value, "billSource");
            return (Criteria) this;
        }

        public Criteria andBillSourceIn(List<String> values) {
            addCriterion("bill_source in", values, "billSource");
            return (Criteria) this;
        }

        public Criteria andBillSourceNotIn(List<String> values) {
            addCriterion("bill_source not in", values, "billSource");
            return (Criteria) this;
        }

        public Criteria andBillSourceBetween(String value1, String value2) {
            addCriterion("bill_source between", value1, value2, "billSource");
            return (Criteria) this;
        }

        public Criteria andBillSourceNotBetween(String value1, String value2) {
            addCriterion("bill_source not between", value1, value2, "billSource");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNull() {
            addCriterion("order_time is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNotNull() {
            addCriterion("order_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeEqualTo(LocalDateTime value) {
            addCriterion("order_time =", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotEqualTo(LocalDateTime value) {
            addCriterion("order_time <>", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThan(LocalDateTime value) {
            addCriterion("order_time >", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("order_time >=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThan(LocalDateTime value) {
            addCriterion("order_time <", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("order_time <=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIn(List<LocalDateTime> values) {
            addCriterion("order_time in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotIn(List<LocalDateTime> values) {
            addCriterion("order_time not in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("order_time between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("order_time not between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andDepartureTimeIsNull() {
            addCriterion("departure_time is null");
            return (Criteria) this;
        }

        public Criteria andDepartureTimeIsNotNull() {
            addCriterion("departure_time is not null");
            return (Criteria) this;
        }

        public Criteria andDepartureTimeEqualTo(LocalDateTime value) {
            addCriterion("departure_time =", value, "departureTime");
            return (Criteria) this;
        }

        public Criteria andDepartureTimeNotEqualTo(LocalDateTime value) {
            addCriterion("departure_time <>", value, "departureTime");
            return (Criteria) this;
        }

        public Criteria andDepartureTimeGreaterThan(LocalDateTime value) {
            addCriterion("departure_time >", value, "departureTime");
            return (Criteria) this;
        }

        public Criteria andDepartureTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("departure_time >=", value, "departureTime");
            return (Criteria) this;
        }

        public Criteria andDepartureTimeLessThan(LocalDateTime value) {
            addCriterion("departure_time <", value, "departureTime");
            return (Criteria) this;
        }

        public Criteria andDepartureTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("departure_time <=", value, "departureTime");
            return (Criteria) this;
        }

        public Criteria andDepartureTimeIn(List<LocalDateTime> values) {
            addCriterion("departure_time in", values, "departureTime");
            return (Criteria) this;
        }

        public Criteria andDepartureTimeNotIn(List<LocalDateTime> values) {
            addCriterion("departure_time not in", values, "departureTime");
            return (Criteria) this;
        }

        public Criteria andDepartureTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("departure_time between", value1, value2, "departureTime");
            return (Criteria) this;
        }

        public Criteria andDepartureTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("departure_time not between", value1, value2, "departureTime");
            return (Criteria) this;
        }

        public Criteria andOrderCreateDateIsNull() {
            addCriterion("order_create_date is null");
            return (Criteria) this;
        }

        public Criteria andOrderCreateDateIsNotNull() {
            addCriterion("order_create_date is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCreateDateEqualTo(LocalDateTime value) {
            addCriterion("order_create_date =", value, "orderCreateDate");
            return (Criteria) this;
        }

        public Criteria andOrderCreateDateNotEqualTo(LocalDateTime value) {
            addCriterion("order_create_date <>", value, "orderCreateDate");
            return (Criteria) this;
        }

        public Criteria andOrderCreateDateGreaterThan(LocalDateTime value) {
            addCriterion("order_create_date >", value, "orderCreateDate");
            return (Criteria) this;
        }

        public Criteria andOrderCreateDateGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("order_create_date >=", value, "orderCreateDate");
            return (Criteria) this;
        }

        public Criteria andOrderCreateDateLessThan(LocalDateTime value) {
            addCriterion("order_create_date <", value, "orderCreateDate");
            return (Criteria) this;
        }

        public Criteria andOrderCreateDateLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("order_create_date <=", value, "orderCreateDate");
            return (Criteria) this;
        }

        public Criteria andOrderCreateDateIn(List<LocalDateTime> values) {
            addCriterion("order_create_date in", values, "orderCreateDate");
            return (Criteria) this;
        }

        public Criteria andOrderCreateDateNotIn(List<LocalDateTime> values) {
            addCriterion("order_create_date not in", values, "orderCreateDate");
            return (Criteria) this;
        }

        public Criteria andOrderCreateDateBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("order_create_date between", value1, value2, "orderCreateDate");
            return (Criteria) this;
        }

        public Criteria andOrderCreateDateNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("order_create_date not between", value1, value2, "orderCreateDate");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(LocalDateTime value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(LocalDateTime value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(LocalDateTime value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(LocalDateTime value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<LocalDateTime> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<LocalDateTime> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("created_by is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("created_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(String value) {
            addCriterion("created_by =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(String value) {
            addCriterion("created_by <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(String value) {
            addCriterion("created_by >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(String value) {
            addCriterion("created_by >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(String value) {
            addCriterion("created_by <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(String value) {
            addCriterion("created_by <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLike(String value) {
            addCriterion("created_by like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotLike(String value) {
            addCriterion("created_by not like", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<String> values) {
            addCriterion("created_by in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<String> values) {
            addCriterion("created_by not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(String value1, String value2) {
            addCriterion("created_by between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(String value1, String value2) {
            addCriterion("created_by not between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(LocalDateTime value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(LocalDateTime value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(LocalDateTime value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(LocalDateTime value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<LocalDateTime> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<LocalDateTime> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Boolean value) {
            addCriterion("is_deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Boolean value) {
            addCriterion("is_deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Boolean value) {
            addCriterion("is_deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Boolean value) {
            addCriterion("is_deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Boolean> values) {
            addCriterion("is_deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Boolean> values) {
            addCriterion("is_deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        private DispatchBillInfoExample example;

        protected Criteria(DispatchBillInfoExample example) {
            super();
            this.example = example;
        }

        public DispatchBillInfoExample example() {
            return this.example;
        }

        @Deprecated
        public Criteria andIf(boolean ifAdd, ICriteriaAdd add) {
            if (ifAdd) {
                add.add(this);
            }
            return this;
        }

        public Criteria when(boolean condition, ICriteriaWhen then) {
            if (condition) {
                then.criteria(this);
            }
            return this;
        }

        public Criteria when(boolean condition, ICriteriaWhen then, ICriteriaWhen otherwise) {
            if (condition) {
                then.criteria(this);
            } else {
                otherwise.criteria(this);
            }
            return this;
        }

        @Deprecated
        public interface ICriteriaAdd {
            Criteria add(Criteria add);
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }

    public interface ICriteriaWhen {
        void criteria(Criteria criteria);
    }

    public interface IExampleWhen {
        void example(DispatchBillInfoExample example);
    }
}