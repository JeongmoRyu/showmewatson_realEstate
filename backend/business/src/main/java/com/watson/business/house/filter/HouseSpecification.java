package com.watson.business.house.filter;

import com.watson.business.house.domain.entity.House;
import com.watson.business.house.domain.entity.housecontractinfodetail.MonthlyInfo;
import com.watson.business.house.domain.entity.housecontractinfodetail.SaleInfo;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

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
    public static Specification<House> graterThanOrEqualMonthlyRent(int minMonthlyRent) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Join<House, MonthlyInfo> contractInfoJoin = root.join("monthlyInfo", JoinType.INNER);
            return criteriaBuilder.greaterThanOrEqualTo(contractInfoJoin.get("monthlyRent"), minMonthlyRent);
        };
    }
    public static Specification<House> lessThanOrEqualMonthlyRent(int maxMonthlyRent) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Join<House, MonthlyInfo> monthlyInfoJoin = root.join("monthlyInfo", JoinType.INNER);
            return criteriaBuilder.lessThanOrEqualTo(monthlyInfoJoin.get("monthlyRent"), maxMonthlyRent);
        };
    }
    //      [범위 : 최소, 최대] 보증금(월세)
    public static Specification<House> graterThanOrEqualDeposit(int minDeposit) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Join<House, MonthlyInfo> monthlyInfoJoin = root.join("monthlyInfo", JoinType.INNER);
            return criteriaBuilder.greaterThanOrEqualTo(monthlyInfoJoin.get("deposit"), minDeposit);
        };
    }
    public static Specification<House> lessThanOrEqualDeposit(int maxDeposit) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Join<House, MonthlyInfo> monthlyInfoJoin = root.join("monthlyInfo", JoinType.INNER);
            return criteriaBuilder.lessThanOrEqualTo(monthlyInfoJoin.get("deposit"), maxDeposit);
        };
    }
    //      [범위 : 최소, 최대] 관리비(월세)
    public static Specification<House> graterThanOrEqualMaintenance(int minMaintenance) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Join<House, MonthlyInfo> monthlyInfoJoin = root.join("monthlyInfo", JoinType.INNER);
            return criteriaBuilder.greaterThanOrEqualTo(monthlyInfoJoin.get("maintenance"), minMaintenance);
        };
    }
    public static Specification<House> lessThanOrEqualMaintenance(int maxMaintenance) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Join<House, MonthlyInfo> monthlyInfoJoin = root.join("monthlyInfo", JoinType.INNER);
            return criteriaBuilder.lessThanOrEqualTo(monthlyInfoJoin.get("maintenance"), maxMaintenance);
        };
    }
    //      [범위 : 최소, 최대] 매매가
    public static Specification<House> graterThanSalePrice(int minSalePrice) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Join<House, SaleInfo> contractInfoJoin = root.join("saleInfo", JoinType.INNER);
            return criteriaBuilder.greaterThanOrEqualTo(contractInfoJoin.get("salePrice"), minSalePrice);
        };
    }
    public static Specification<House> lessThanSalePrice(int maxSalePrice) {
        return (Root<House> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Join<House, SaleInfo> contractInfoJoin = root.join("saleInfo", JoinType.INNER);
            return criteriaBuilder.lessThanOrEqualTo(contractInfoJoin.get("salePrice"), maxSalePrice);
        };
    }

}