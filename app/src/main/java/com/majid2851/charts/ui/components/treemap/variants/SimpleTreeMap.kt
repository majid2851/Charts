package com.majid2851.charts.ui.components.treemap.variants

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.majid2851.charts.domain.model.TreeMapData
import com.majid2851.charts.domain.model.TreeMapNode
import com.majid2851.charts.ui.components.treemap.TreeMapChart

@Composable
fun SimpleTreeMap(
    modifier: Modifier = Modifier
) {
    val data = TreeMapData(
        title = "Simple TreeMap",
        nodes = listOf(
            TreeMapNode(
                name = "axis",
                children = listOf(
                    TreeMapNode("Axes", size = 1302f),
                    TreeMapNode("Axis", size = 24593f),
                    TreeMapNode("AxisGridLine", size = 652f),
                    TreeMapNode("AxisLabel", size = 636f),
                    TreeMapNode("CartesianAxes", size = 6703f)
                )
            ),
            TreeMapNode(
                name = "controls",
                children = listOf(
                    TreeMapNode("AnchorControl", size = 2138f),
                    TreeMapNode("ClickControl", size = 3824f),
                    TreeMapNode("Control", size = 1353f),
                    TreeMapNode("ControlList", size = 4665f),
                    TreeMapNode("DragControl", size = 2649f),
                    TreeMapNode("ExpandControl", size = 2832f),
                    TreeMapNode("HoverControl", size = 4896f),
                    TreeMapNode("IControl", size = 763f),
                    TreeMapNode("PanZoomControl", size = 5222f),
                    TreeMapNode("SelectionControl", size = 7862f),
                    TreeMapNode("TooltipControl", size = 8435f)
                )
            ),
            TreeMapNode(
                name = "data",
                children = listOf(
                    TreeMapNode("Data", size = 20544f),
                    TreeMapNode("DataList", size = 19788f),
                    TreeMapNode("DataSprite", size = 10349f),
                    TreeMapNode("EdgeSprite", size = 3301f),
                    TreeMapNode("NodeSprite", size = 19382f),
                    TreeMapNode(
                        name = "render",
                        children = listOf(
                            TreeMapNode("ArrowType", size = 698f),
                            TreeMapNode("EdgeRenderer", size = 5569f),
                            TreeMapNode("IRenderer", size = 353f),
                            TreeMapNode("ShapeRenderer", size = 2247f)
                        )
                    ),
                    TreeMapNode("ScaleBinding", size = 11275f),
                    TreeMapNode("Tree", size = 7147f),
                    TreeMapNode("TreeBuilder", size = 9930f)
                )
            ),
            TreeMapNode(
                name = "events",
                children = listOf(
                    TreeMapNode("DataEvent", size = 7313f),
                    TreeMapNode("SelectionEvent", size = 6880f),
                    TreeMapNode("TooltipEvent", size = 3701f),
                    TreeMapNode("VisualizationEvent", size = 2117f)
                )
            ),
            TreeMapNode(
                name = "legend",
                children = listOf(
                    TreeMapNode("Legend", size = 20859f),
                    TreeMapNode("LegendItem", size = 4614f),
                    TreeMapNode("LegendRange", size = 10530f)
                )
            ),
            TreeMapNode(
                name = "operator",
                children = listOf(
                    TreeMapNode(
                        name = "distortion",
                        children = listOf(
                            TreeMapNode("BifocalDistortion", size = 4461f),
                            TreeMapNode("Distortion", size = 6314f),
                            TreeMapNode("FisheyeDistortion", size = 3444f)
                        )
                    ),
                    TreeMapNode(
                        name = "encoder",
                        children = listOf(
                            TreeMapNode("ColorEncoder", size = 3179f),
                            TreeMapNode("Encoder", size = 4060f),
                            TreeMapNode("PropertyEncoder", size = 4138f),
                            TreeMapNode("ShapeEncoder", size = 1690f),
                            TreeMapNode("SizeEncoder", size = 1830f)
                        )
                    )
                )
            )
        ),
        strokeColor = Color.White,
        defaultFill = Color(0xFF8884d8),
        aspectRatio = 4f / 3f,
        showLabels = true
    )

    TreeMapChart(
        data = data,
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 500, heightDp = 400)
@Composable
private fun SimpleTreeMapPreview() {
    SimpleTreeMap(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    )
}

