package com.watson.business.house.filter;

import com.watson.business.house.domain.entity.House;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class HouseSpecification {
    //      [일치] 방 코드
    public static Specification<House> equalHouseCode(int houseCode) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("houseCode"), houseCode);
        };
    }

    //      [일치] 거래 코드
    public static Specification<House> equalContractCode(int contractCode) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("contractCode"), contractCode);
        };
    }
    //      [일치] 법정동 코드
    public static Specification<House> equalHouseCode(String dongCode) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("dongCode"), dongCode);
        };
    }
    //      [범위 : 최소, 최대] 면적
    public static Specification<House> graterThanSquareMeter(int minSquareMeter) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("squareMeter"), minSquareMeter);
        };
    }
    public static Specification<House> lessThanSquareMeter(int maxSquareMeter) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("squareMeter"), maxSquareMeter);
        };
    }
    //      [범위 : 최소, 최대] 월세
    public static Specification<House> graterThanMonthlyRent(int minMonthlyRent) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("monthlyRent"), minMonthlyRent);
        };
    }
    public static Specification<House> lessThanMonthlyRent(int maxMonthlyRent) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("monthlyRent"), maxMonthlyRent);
        };
    }
    //      [범위 : 최소, 최대] 보증금
    public static Specification<House> graterThanDeposit(int minDeposit) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("deposit"), minDeposit);
        };
    }
    public static Specification<House> lessThanDeposit(int maxDeposit) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("deposit"), maxDeposit);
        };
    }
    //      [범위 : 최소, 최대] 관리비
    public static Specification<House> graterThanMaintenance(int minMaintenance) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("maintenance"), minMaintenance);
        };
    }
    public static Specification<House> lessThanMaintenance(int maxMaintenance) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("maintenance"), maxMaintenance);
        };
    }
    //      [범위 : 최소, 최대] 매매가
    public static Specification<House> graterThanSalePrice(int minSalePrice) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("salePrice"), minSalePrice);
        };
    }
    public static Specification<House> lessThanSalePrice(int maxSalePrice) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("salePrice"), maxSalePrice);
        };
    }

}
