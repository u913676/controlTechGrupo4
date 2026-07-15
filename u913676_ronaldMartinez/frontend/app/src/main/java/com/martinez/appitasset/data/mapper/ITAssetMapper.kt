package com.martinez.appitasset.data.mapper

import com.martinez.appitasset.data.remote.dto.ITAssetDto
import com.martinez.appitasset.data.remote.dto.addITAssetRequest
import com.martinez.appitasset.domain.model.ITAssetModel

object ITAssetMapper {
    fun toDomain(dto: ITAssetDto) : ITAssetModel =
        ITAssetModel(
            dto.id,
            dto.equipmentType,
            dto.brand,
            dto.model,
            dto.serialNumber,
            dto.assetTag,
            dto.status,
            dto.location,
            dto.asignedUser,
            dto.purchaseDate,
            dto.notes
        )

    fun toInsertITAssetRequest(model: ITAssetModel) : addITAssetRequest =
        addITAssetRequest(
            model.equipmentType,
            model.brand,
            model.model,
            model.serialNumber,
            model.assetTag,
            model.status,
            model.location,
            model.asignedUser,
            model.purchaseDate,
            model.notes
        )
}