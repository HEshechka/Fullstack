package net.petproject.backend.mapper.supplier;

import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderRequestDTO;
import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderResponseDTO;
import net.petproject.backend.model.supplier.SupplierOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {SupplierOrderItemMapper.class, SupplierMapper.class})
public interface SupplierOrderMapper {
    @Mapping(target = "supplierId", source = "supplierOrder.supplier.id")
    @Mapping(target = "supplierName", source = "supplierOrder.supplier.supplierName")
    @Mapping(target = "items", source = "supplierOrder.supplierOrderItems")
    SupplierOrderResponseDTO toDto(SupplierOrder supplierOrder);

    @Mapping(target = "supplier", ignore = true) // Supplier будет установлен в сервисе
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDate", ignore = true) // Устанавливается на сервере
    @Mapping(target = "totalAmount", ignore = true) // Вычисляется в сервисе
    @Mapping(target = "supplierOrderItems", ignore = true) // Items будут добавлены в сервисе
    SupplierOrder toEntity(SupplierOrderRequestDTO supplierOrderRequestDTO);
}
