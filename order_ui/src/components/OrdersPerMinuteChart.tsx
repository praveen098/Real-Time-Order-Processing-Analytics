'use client'

import { useEffect, useState } from 'react'
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
} from 'recharts'

interface DataPoint {
  minute: string
  count: number
}

export default function OrdersPerMinuteChart() {
  const [data, setData] = useState<DataPoint[]>([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    async function fetchData() {
      try {
        const res = await fetch('/api/orders-per-minute')
        const json = await res.json()
        // if your API wrapped it in { rows: [...] }, pull that out
        const rows: any[] = Array.isArray(json) ? json : json.rows || []
        const parsed: DataPoint[] = rows.map((d) => ({
          minute: new Date(d.minute).toLocaleTimeString([], {
            hour: '2-digit',
            minute: '2-digit',
          }),
          count: Number(d.count),
        }))
        setData(parsed)
      } catch (err) {
        console.error('Failed to fetch orders per minute', err)
      } finally {
        setLoading(false)
      }
    }

    fetchData()
    const id = setInterval(fetchData, 10_000)
    return () => clearInterval(id)
  }, [])

  if (loading) return <div className="text-center p-4">Loading...</div>

  return (
    <div className="p-4 bg-white shadow rounded-lg">
      <h2 className="text-xl font-semibold mb-4">Orders Per Minute</h2>
      <ResponsiveContainer width="100%" height={300}>
        <LineChart data={data}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis dataKey="minute" />
          <YAxis allowDecimals={false} />
          <Tooltip />
          <Line type="monotone" dataKey="count" strokeWidth={2} dot={false} />
        </LineChart>
      </ResponsiveContainer>
    </div>
  )
}
