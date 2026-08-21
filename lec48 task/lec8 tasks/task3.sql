SELECT *
FROM job_history
WHERE job_id IN (
    'AD_ASST',
    'FI_MGR',
    'FI_ACCOUNT',
    'AC_MGR',
    'AC_ACCOUNT',
    'SA_MAN',
    'SA_REP',
    'PU_MAN'
);

SELECT *
FROM departments
WHERE department_name IN (
    'Administration',
    'Marketing',
    'Purchasing',
    'Human Resources',
    'Shipping'
);
