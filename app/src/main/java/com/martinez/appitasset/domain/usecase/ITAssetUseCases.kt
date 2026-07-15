package com.martinez.appitasset.domain.usecase

data class ITAssetUseCases(
    val getITAssets: GetITAssetsUseCase,
    val addITAsset: AddITAssetUseCase,
    val getITAsset: GetITAssetUseCase,
    val updateITAsset: UpdateITAssetUseCase,
    val deleteITAsset : DeleteITAssetUseCase
)
