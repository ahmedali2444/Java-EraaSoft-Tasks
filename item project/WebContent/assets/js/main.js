document.addEventListener("DOMContentLoaded",function(){
    var sidebar=document.querySelector("[data-sidebar]");
    var overlay=document.querySelector(".sidebar-overlay");
    function setSidebar(open){if(!sidebar)return;sidebar.classList.toggle("open",open);overlay.classList.toggle("open",open);}
    document.querySelectorAll("[data-sidebar-open]").forEach(function(button){button.addEventListener("click",function(){setSidebar(true);});});
    document.querySelectorAll("[data-sidebar-close]").forEach(function(button){button.addEventListener("click",function(){setSidebar(false);});});
    document.querySelectorAll("[data-modal-open]").forEach(function(button){button.addEventListener("click",function(){var modal=document.getElementById(button.dataset.modalOpen);if(!modal)return;var form=modal.querySelector("form");if(form)form.reset();if(modal.id==="productModal"){form.elements.namedItem("id").value="0";clearCategorySelection();form.querySelector("[data-product-title]").textContent="Add product";productStep(1);}if(modal.id==="categoryFormModal"&&form.hasAttribute("data-category-form")){form.elements.namedItem("action").value="add";form.elements.namedItem("id").value="0";form.querySelector("[data-category-title]").textContent="Add category";}if(modal.id==="employeeModal"){form.elements.namedItem("id").value="0";form.elements.namedItem("password").required=true;form.elements.namedItem("active").checked=true;form.querySelector("[data-employee-title]").textContent="Add employee";}modal.showModal();});});
    document.querySelectorAll("[data-modal-close]").forEach(function(button){button.addEventListener("click",function(){button.closest("dialog").close();});});
    document.querySelectorAll("dialog").forEach(function(dialog){dialog.addEventListener("click",function(event){if(event.target===dialog)dialog.close();});});
    var confirmModal=document.getElementById("confirmModal");
    var pendingForm=null;
    if(confirmModal){
        document.querySelectorAll("[data-confirm]").forEach(function(form){form.addEventListener("submit",function(event){if(form.dataset.confirmed==="true"){delete form.dataset.confirmed;return;}event.preventDefault();pendingForm=form;confirmModal.querySelector("[data-confirm-message]").textContent=form.dataset.confirm;confirmModal.showModal();});});
        confirmModal.querySelector("[data-confirm-cancel]").addEventListener("click",function(){confirmModal.close();});
        confirmModal.querySelector("[data-confirm-approve]").addEventListener("click",function(){if(!pendingForm)return;var form=pendingForm;confirmModal.close();form.dataset.confirmed="true";form.requestSubmit();});
        confirmModal.addEventListener("close",function(){pendingForm=null;});
    }
    document.querySelectorAll("[data-password-toggle]").forEach(function(button){button.addEventListener("click",function(){var input=document.getElementById(button.dataset.passwordToggle);input.type=input.type==="password"?"text":"password";});});
    document.querySelectorAll(".table-card .table-wrap table").forEach(function(table){
        var rows=Array.from(table.querySelectorAll("tbody tr"));
        if(!rows.length)return;
        var card=table.closest(".table-card");
        var pagination=document.createElement("div");
        var previous=document.createElement("button");
        var status=document.createElement("span");
        var nextPage=document.createElement("button");
        var page=1;
        var pageSize=5;
        pagination.className="pagination";
        previous.type="button";
        previous.textContent="Previous";
        nextPage.type="button";
        nextPage.textContent="Next";
        pagination.appendChild(previous);
        pagination.appendChild(status);
        pagination.appendChild(nextPage);
        card.appendChild(pagination);
        function renderPagination(){
            var matching=rows.filter(function(row){return row.dataset.searchMatch!=="false";});
            var pages=Math.max(1,Math.ceil(matching.length/pageSize));
            if(page>pages)page=pages;
            rows.forEach(function(row){
                var index=matching.indexOf(row);
                row.hidden=index===-1||index<(page-1)*pageSize||index>=page*pageSize;
            });
            status.textContent=matching.length?"Page "+page+" of "+pages:"No results";
            previous.disabled=page===1;
            nextPage.disabled=page===pages;
            pagination.hidden=pages===1;
        }
        previous.addEventListener("click",function(){if(page>1){page--;renderPagination();}});
        nextPage.addEventListener("click",function(){if(page<Math.ceil(rows.filter(function(row){return row.dataset.searchMatch!=="false";}).length/pageSize)){page++;renderPagination();}});
        table.renderPagination=renderPagination;
        table.resetPagination=function(){page=1;renderPagination();};
        renderPagination();
    });
    var search=document.querySelector("[data-table-search]");
    if(search)search.addEventListener("input",function(){
        var value=search.value.toLowerCase().trim();
        var table=document.querySelector(".table-card .table-wrap table");
        if(!table)return;
        table.querySelectorAll("[data-search-row]").forEach(function(row){row.dataset.searchMatch=row.dataset.searchValue.includes(value)?"true":"false";});
        table.resetPagination();
    });
    var productForm=document.querySelector("[data-product-form]");
    function productStep(step){if(!productForm)return;productForm.querySelector('[data-product-step="1"]').hidden=step!==1;productForm.querySelector('[data-product-step="2"]').hidden=step!==2;var indicators=productForm.querySelectorAll(".step-indicator span");indicators[0].classList.toggle("active",step===1);indicators[1].classList.toggle("active",step===2);productForm.querySelector("[data-product-subtitle]").textContent="Step "+step+" of 2";}
    function setFieldError(input,message){if(!input)return;var label=input.closest("label");var error=label.querySelector("[data-field-error]");if(!error){error=document.createElement("span");error.className="field-error";error.setAttribute("data-field-error","");label.appendChild(error);}input.classList.toggle("input-error",Boolean(message));error.textContent=message||"";}
    function clearFieldError(input){setFieldError(input,"");}
    function clearProductErrors(){if(!productForm)return;productForm.querySelectorAll(".input-error").forEach(function(input){clearFieldError(input);});}
    function validateProductBasics(){
        if(!productForm)return false;
        var nameInput=productForm.elements.namedItem("name");
        var priceInput=productForm.elements.namedItem("price");
        var quantityInput=productForm.elements.namedItem("quantity");
        var valid=true;
        updateCategorySelection();
        if(!nameInput.value.trim()){setFieldError(nameInput,"Enter the product name.");valid=false;}else clearFieldError(nameInput);
        if(!categoryIdInput.value){setFieldError(categoryInput,"Choose a category from the list.");valid=false;}else clearFieldError(categoryInput);
        var price=Number(priceInput.value);
        if(priceInput.value.trim()===""||Number.isNaN(price)||price<0){setFieldError(priceInput,"Enter a valid price.");valid=false;}else clearFieldError(priceInput);
        var quantity=Number(quantityInput.value);
        if(quantityInput.value.trim()===""||!Number.isInteger(quantity)||quantity<0){setFieldError(quantityInput,"Enter a valid quantity.");valid=false;}else clearFieldError(quantityInput);
        return valid;
    }
    var next=document.querySelector("[data-next-step]");if(next)next.addEventListener("click",function(){if(validateProductBasics())productStep(2);});
    var previous=document.querySelector("[data-previous-step]");if(previous)previous.addEventListener("click",function(){productStep(1);});
    document.querySelectorAll("[data-edit-product]").forEach(function(button){button.addEventListener("click",function(){productForm.reset();clearProductErrors();productForm.elements.namedItem("id").value=button.dataset.id;productForm.elements.namedItem("name").value=button.dataset.name;var selectedCategory=Array.from(document.querySelectorAll("[data-category-option]")).find(function(option){return option.dataset.id===button.dataset.categoryId;});categoryInput.value=selectedCategory?selectedCategory.dataset.name:"";categoryIdInput.value=button.dataset.categoryId;categoryInput.setCustomValidity("");productForm.elements.namedItem("price").value=button.dataset.price;productForm.elements.namedItem("quantity").value=button.dataset.quantity;productForm.elements.namedItem("description").value=button.dataset.description;productForm.elements.namedItem("issueDate").value=button.dataset.issue;productForm.elements.namedItem("expiryDate").value=button.dataset.expiry;productForm.querySelector("[data-product-title]").textContent="Edit product";productStep(1);document.getElementById("productModal").showModal();});});
    document.querySelectorAll("[data-details]").forEach(function(button){button.addEventListener("click",function(){var modal=document.getElementById("detailsModal");modal.querySelector("[data-detail-name]").textContent=button.dataset.name;modal.querySelector("[data-detail-category]").textContent=button.dataset.category;modal.querySelector("[data-detail-price]").textContent="$ "+Number(button.dataset.price).toFixed(2);modal.querySelector("[data-detail-quantity]").textContent=button.dataset.quantity;modal.querySelector("[data-detail-description]").textContent=button.dataset.description||"No description added.";modal.querySelector("[data-detail-issue]").textContent=button.dataset.issue||"Not specified";modal.querySelector("[data-detail-expiry]").textContent=button.dataset.expiry||"Not specified";modal.showModal();});});
    var categoryInput=document.querySelector("[data-category-input]");
    var categoryIdInput=document.querySelector("[data-category-id]");
    var categoryCombobox=document.querySelector("[data-category-combobox]");
    var categoryOptions=document.querySelector("[data-category-options]");
    var categoryToggle=document.querySelector("[data-category-toggle]");
    function categoryOption(value){var search=value.trim().toLowerCase();return Array.from(document.querySelectorAll("[data-category-option]")).find(function(option){return option.dataset.name.toLowerCase()===search;});}
    function closeCategoryOptions(){if(categoryOptions)categoryOptions.hidden=true;}
    function showCategoryOptions(){if(!categoryOptions)return;var value=categoryInput.value.trim().toLowerCase();document.querySelectorAll("[data-category-option]").forEach(function(option){option.hidden=!option.dataset.name.toLowerCase().includes(value);});categoryOptions.hidden=false;}
    function updateCategorySelection(){if(!categoryInput)return;var option=categoryOption(categoryInput.value);if(option){categoryIdInput.value=option.dataset.id;categoryInput.setCustomValidity("");return;}categoryIdInput.value="";categoryInput.setCustomValidity(categoryInput.value.trim()===""?"":"Choose a category from the list.");}
    function chooseCategory(option){categoryInput.value=option.dataset.name;categoryIdInput.value=option.dataset.id;categoryInput.setCustomValidity("");closeCategoryOptions();}
    function clearCategorySelection(){if(!categoryInput)return;categoryInput.value="";categoryIdInput.value="";categoryInput.setCustomValidity("");closeCategoryOptions();}
    if(categoryInput){categoryInput.addEventListener("input",function(){clearFieldError(categoryInput);updateCategorySelection();showCategoryOptions();});categoryInput.addEventListener("focus",showCategoryOptions);categoryInput.addEventListener("change",updateCategorySelection);categoryToggle.addEventListener("click",function(){if(categoryOptions.hidden)showCategoryOptions();else closeCategoryOptions();});document.querySelectorAll("[data-category-option]").forEach(function(option){option.addEventListener("click",function(){chooseCategory(option);clearFieldError(categoryInput);});});document.addEventListener("click",function(event){if(!categoryCombobox.contains(event.target))closeCategoryOptions();});}
    if(productForm){["name","price","quantity"].forEach(function(name){productForm.elements.namedItem(name).addEventListener("input",function(){clearFieldError(productForm.elements.namedItem(name));});});productForm.addEventListener("submit",function(event){if(!validateProductBasics()){productStep(1);event.preventDefault();}});document.getElementById("productModal").addEventListener("close",clearProductErrors);}
    var categoryForm=document.querySelector("[data-category-form]");document.querySelectorAll("[data-edit-category]").forEach(function(button){button.addEventListener("click",function(){categoryForm.elements.namedItem("action").value="update";categoryForm.elements.namedItem("id").value=button.dataset.id;categoryForm.elements.namedItem("name").value=button.dataset.name;categoryForm.querySelector("[data-category-title]").textContent="Edit category";document.getElementById("categoryFormModal").showModal();});});
    var employeeForm=document.querySelector("[data-employee-form]");document.querySelectorAll("[data-edit-employee]").forEach(function(button){button.addEventListener("click",function(){employeeForm.reset();employeeForm.elements.namedItem("id").value=button.dataset.id;employeeForm.elements.namedItem("name").value=button.dataset.name;employeeForm.elements.namedItem("phone").value=button.dataset.phone;employeeForm.elements.namedItem("email").value=button.dataset.email;employeeForm.elements.namedItem("admin").checked=button.dataset.admin==="true";employeeForm.elements.namedItem("updateProducts").checked=button.dataset.up==="true";employeeForm.elements.namedItem("deleteProducts").checked=button.dataset.dp==="true";employeeForm.elements.namedItem("updateCategories").checked=button.dataset.uc==="true";employeeForm.elements.namedItem("deleteCategories").checked=button.dataset.dc==="true";employeeForm.elements.namedItem("active").checked=button.dataset.active==="true";employeeForm.elements.namedItem("password").required=false;employeeForm.querySelector("[data-employee-title]").textContent="Edit employee";document.getElementById("employeeModal").showModal();});});
});
