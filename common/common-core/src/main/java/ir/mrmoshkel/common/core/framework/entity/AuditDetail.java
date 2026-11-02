package ir.mrmoshkel.common.core.framework.entity;

import ir.mrmoshkel.common.core.user.entity.User;

import java.sql.Date;

public class AuditDetail {
    private Date createdDate;
    private Date modifiedDate;
    private User createdBy;
    private User lastModifiedBy;
}
