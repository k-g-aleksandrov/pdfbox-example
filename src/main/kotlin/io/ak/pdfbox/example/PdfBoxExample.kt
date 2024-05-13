package io.ak.pdfbox.example

import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.PDFRenderer

val topLevelClass = object {}.javaClass.enclosingClass

fun main() {
    topLevelClass.getResourceAsStream("/simple_sign_form.pdf")?.use { inStream ->
        Loader.loadPDF(inStream.readAllBytes()).use {
//            it.renderFirstPage()
            it.getAcroFormField()
        }
    }
}

private fun PDDocument.renderFirstPage() {
    val renderer = PDFRenderer(this)
    val bitmap = renderer.renderImage(0)
    println()
}

private fun PDDocument.getAcroFormField() {
    val acroForm = this?.documentCatalog?.acroForm
        ?: throw IllegalStateException("AcroForm not found")

    val field = acroForm.fields.firstOrNull()
        ?: throw IllegalStateException("AcroForm not found")

    println(field.fullyQualifiedName)
}
