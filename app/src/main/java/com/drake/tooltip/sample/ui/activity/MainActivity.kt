/*
 * MIT License
 *
 * Copyright (c) 2023 劉強東 https://github.com/liangjingkanji
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.drake.tooltip.sample.ui.activity

import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.drake.engine.base.EngineActivity
import com.drake.statusbar.immersive
import com.drake.tooltip.sample.R
import com.drake.tooltip.sample.databinding.ActivityMainBinding

class MainActivity : EngineActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initView() {
        setSupportActionBar(binding.toolbar)
        immersive(binding.toolbar, true)

        val navController = findNavController(R.id.fragment)
        binding.toolbar.setupWithNavController(
            navController,
            AppBarConfiguration(binding.drawerNav.menu, binding.drawer)
        )
        binding.drawerNav.setupWithNavController(navController)
    }

    override fun initData() {
    }

    override fun onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }

}
