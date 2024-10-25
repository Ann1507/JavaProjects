select  productName,unitPrice,categoryName 
from category right join product on product.categoryId=category.categoryId
 where discontinued=0
 order by categoryName,productName
