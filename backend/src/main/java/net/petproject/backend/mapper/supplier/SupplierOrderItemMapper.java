package net.petproject.backend.mapper.supplier;

import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderItemRequestDTO;
import net.petproject.backend.dto.supplier.supplier_order.SupplierOrderItemResponseDTO;
import net.petproject.backend.mapper.product.ProductMapper;
import net.petproject.backend.model.supplier.SupplierOrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {ProductMapper.class}) // Используем ProductMapper
public interface SupplierOrderItemMapper {
    @Mapping(target = "supplierOrderId", source = "supplierOrderItem.supplierOrder.id")
    @Mapping(target = "productId", source = "supplierOrderItem.product.id")
    @Mapping(target = "productName", source = "supplierOrderItem.product.productName")
    SupplierOrderItemResponseDTO toDto(SupplierOrderItem supplierOrderItem);

    @Mapping(target = "supplierOrder", ignore = true) // Order и Product будут установлены в сервисе
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "id", ignore = true)
    SupplierOrderItem toEntity(SupplierOrderItemRequestDTO supplierOrderItemRequestDTO);
}
