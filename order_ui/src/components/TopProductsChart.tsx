'use client'

import { useEffect, useState } from 'react'
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
} from 'recharts'

interface ProductPoint {
  product: string
  totalSales: number
}

export default function TopProductsChart() {
  const [data, setData] = useState<ProductPoint[]>([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    async function fetchTopProducts() {
      try {
        const res = await fetch('/api/top-products')
        const json = await res.json()
        const rows: any[] = Array.isArray(json) ? json : json.rows || []
        const parsed = rows.map((r) => ({
          product: r.product,
          totalSales: Number(r.totalSales),
        }))
        setData(parsed)
      } catch (err) {
        console.error('Failed to fetch top products', err)
      } finally {
        setLoading(false)
      }
    }
    fetchTopProducts()
  }, [])

  if (loading) return <div className="text-center p-4">Loading...</div>

  return (
    <div className="p-4 bg-white shadow rounded-lg">
      <h2 className="text-xl font-semibold mb-4">Top Products (Last Hour)</h2>
      <ResponsiveContainer width="100%" height={300}>
        <BarChart data={data}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="product" tick={{ fontSize: 12 }} />
          <YAxis />
          <Tooltip />
          <Bar dataKey="totalSales" barSize={24} />
        </BarChart>
      </ResponsiveContainer>
    </div>
  )
}
